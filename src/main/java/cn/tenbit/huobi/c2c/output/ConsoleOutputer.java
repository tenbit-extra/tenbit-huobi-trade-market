package cn.tenbit.huobi.c2c.output;

import cn.tenbit.hare.core.lite.util.HarePrintUtils;
import cn.tenbit.hare.core.lite.util.HareStringUtils;
import cn.tenbit.hare.core.lite.util.HareTimeUtils;
import cn.tenbit.huobi.common.output.Outputer;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 15:38
 */
public class ConsoleOutputer extends AbstractStringQueueOutputer implements Outputer<String> {

    @Override
    protected void doOutput(String item) {
        HarePrintUtils.newline();
        HarePrintUtils.console("----------------------- " + HareTimeUtils.currentFormatTime() + " ----------------------- ");
        HarePrintUtils.console(HareStringUtils.toNotNullString(item));
        HarePrintUtils.console("-------------------------------------------------------------------------");
        HarePrintUtils.newline();
    }

}
