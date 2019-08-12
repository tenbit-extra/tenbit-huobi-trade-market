package cn.tenbit.huobi.c2c.invoke;

import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.hare.core.lite.util.HareSleepUtils;
import cn.tenbit.huobi.c2c.analyse.HbBuyAnalyser;
import cn.tenbit.huobi.c2c.analyse.HbSellAnalyser;
import cn.tenbit.huobi.c2c.domain.HbBuyResult;
import cn.tenbit.huobi.c2c.domain.HbSellResult;
import cn.tenbit.huobi.c2c.event.ConsoleOutputListener;
import cn.tenbit.huobi.c2c.event.EmailOutputListener;
import cn.tenbit.huobi.c2c.event.HbEventType;
import cn.tenbit.huobi.c2c.input.HbBuyInputer;
import cn.tenbit.huobi.c2c.input.HbSellInputer;
import cn.tenbit.huobi.c2c.task.HbDailyReporter;
import cn.tenbit.huobi.c2c.watch.HbBuyWatcher;
import cn.tenbit.huobi.c2c.watch.HbSellWatcher;
import cn.tenbit.huobi.common.event.Events;
import cn.tenbit.huobi.common.event.StringEvent;
import cn.tenbit.huobi.common.invoke.Invoker;
import cn.tenbit.huobi.common.threadpool.ThreadPools;
import cn.tenbit.huobi.common.watch.Watcher;
import cn.tenbit.huobi.config.ConfigDefine;
import cn.tenbit.huobi.config.Configs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 16:46
 */
public class HbInvoker implements Invoker {

    private final Watcher<HbBuyResult> buyWatcher = new HbBuyWatcher();

    private final Watcher<HbSellResult> sellWatcher = new HbSellWatcher();

    private final HbDailyReporter dailyReporter = new HbDailyReporter();

    private final ExecutorService pool = ThreadPools.getPool();

    private final AtomicBoolean status = new AtomicBoolean();

    private static final long TIME_OUT = 3;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private static final long SEQUENCE_TIME_OUT = 3;

    private static final TimeUnit SEQUENCE_TIME_UNIT = TimeUnit.SECONDS;

    public HbInvoker() {
        init();
    }

    private void init() {
        initProperties();
        initComponents();
    }

    private void initComponents() {
        buyWatcher.setInputer(new HbBuyInputer(Configs.getProperty(ConfigDefine.HUOBI_C2C_API_BUY)));
        buyWatcher.addAnalyser(new HbBuyAnalyser());

        sellWatcher.setInputer(new HbSellInputer(Configs.getProperty(ConfigDefine.HUOBI_C2C_API_SELL)));
        sellWatcher.addAnalyser(new HbSellAnalyser());

        Events.register(HbEventType.CONSOLE, new ConsoleOutputListener());
        Events.register(HbEventType.EMAIL, new EmailOutputListener());
    }

    private void initProperties() {
        Configs.reloadProperties();
    }

    @Override
    public void work() {
        HareInvokeUtils.invokeWithTurnRe(() -> {
            consoleWork();
            invokeBuyWatcher();
            invokeSellWatcher();
            invokeDailyReporter();
            justStandBy();
            return null;
        });
    }

    private void invokeDailyReporter() {
        pool.submit(() -> {
            while (true) {
                HareSleepUtils.sleep(SEQUENCE_TIME_UNIT, SEQUENCE_TIME_OUT);
                HareInvokeUtils.invokeWithSwallow(() -> {
                    dailyReporter.run();
                    return null;
                });
            }
        });
    }

    private void consoleWork() {
        Events.publish(new StringEvent(HbEventType.CONSOLE, "START WORK"));
        Events.publish(new StringEvent(HbEventType.CONSOLE, ConfigDefine.USER_DIR));
        Events.publish(new StringEvent(HbEventType.CONSOLE, Configs.showProperties()));
    }

    private void justStandBy() {
        status.getAndSet(true);
        while (status.get()) {
            HareInvokeUtils.invokeWithTurnRe(() -> {
                HareSleepUtils.sleep(TIME_UNIT, TIME_OUT);
                return null;
            });
        }
        Events.publish(new StringEvent(HbEventType.CONSOLE, "STOP WORK"));
        HareSleepUtils.sleep(TIME_UNIT, TIME_OUT);
        System.exit(0);
    }

    @Override
    public void exit() {
        HareInvokeUtils.invokeWithTurnRe(() -> {
            try {
                pool.shutdownNow();
                pool.awaitTermination(TIME_OUT, TIME_UNIT);
            } finally {
                status.getAndSet(false);
            }
            return null;
        });
    }

    private void invokeSellWatcher() {
        pool.submit(() -> {
            while (true) {
                HareSleepUtils.sleep(SEQUENCE_TIME_UNIT, SEQUENCE_TIME_OUT);
                HareInvokeUtils.invokeWithSwallow(() -> {
                    sellWatcher.work();
                    return null;
                });
            }
        });
    }

    private void invokeBuyWatcher() {
        pool.submit(() -> {
            while (true) {
                HareSleepUtils.sleep(SEQUENCE_TIME_UNIT, SEQUENCE_TIME_OUT);
                HareInvokeUtils.invokeWithSwallow(() -> {
                    buyWatcher.work();
                    return null;
                });
            }
        });
    }
}
