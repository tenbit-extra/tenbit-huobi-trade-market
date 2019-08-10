package cn.tenbit.huobi.c2c.util;

import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.huobi.common.analyse.Analyser;
import cn.tenbit.huobi.common.threadpool.ThreadPools;
import cn.tenbit.huobi.common.watch.Watcher;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 16:12
 */
public class HbWatchUtils {

    private static final long TIME_OUT = 1;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private static final ExecutorService POOL = ThreadPools.getPool();

    public static <T> void watch(Watcher<T> watcher) {
        List<Analyser<T>> analysers = watcher.getAnalysers();
        if (CollectionUtils.isEmpty(analysers)) {
            return;
        }

        T result = watcher.getInputer().get();
        List<Future> futures = new ArrayList<>(analysers.size());
        for (Analyser<T> analyser : analysers) {
            Future future = POOL.submit(() -> {
                HareInvokeUtils.invokeWithSwallow(() -> {
                    analyser.analyse(result);
                    return null;
                });
                return null;
            });
            if (future != null) {
                futures.add(future);
            }
        }

        for (Future future : futures) {
            HareInvokeUtils.invokeWithTurnRe(() -> {
                future.get(TIME_OUT, TIME_UNIT);
                return null;
            });
        }

        for (Future future : futures) {
            HareInvokeUtils.invokeWithSwallow(() -> {
                if (future.isCancelled()) {
                    return null;
                }
                if (!future.isDone()) {
                    future.cancel(true);
                }
                return null;
            });
        }
    }
}
