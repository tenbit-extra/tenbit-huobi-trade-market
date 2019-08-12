package cn.tenbit.huobi.c2c.task;

import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.hare.core.lite.util.HareSleepUtils;
import cn.tenbit.hare.core.lite.util.HareTimeUtils;
import cn.tenbit.huobi.c2c.event.HbEventType;
import cn.tenbit.huobi.c2c.logic.HbLogic;
import cn.tenbit.huobi.common.event.Events;
import cn.tenbit.huobi.common.event.StringEvent;
import cn.tenbit.huobi.config.ConfigDefine;

/**
 * @Author bangquan.qian
 * @Date 2019-08-12 11:43
 */
public class HbDailyReporter implements Runnable {

    private static final int SLEEP_SECONDS = 1;

    private long nextReportTime = -1;

    @Override
    public void run() {
        while (true) {
            HareInvokeUtils.invokeWithSwallow(() -> {
                if (HareTimeUtils.currentTimeMs() < nextReportTime) {
                    return null;
                }
                nextReportTime = HareTimeUtils.currentTimeMs() + ConfigDefine.ONE_DAY_MS;
                Events.publish(new StringEvent(HbEventType.EMAIL, HbLogic.buildEmail()));
                return null;
            });
            HareSleepUtils.sleepSeconds(SLEEP_SECONDS);
        }
    }
}
