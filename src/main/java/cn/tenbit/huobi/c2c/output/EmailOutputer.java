package cn.tenbit.huobi.c2c.output;

import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.huobi.common.mail.Mails;
import cn.tenbit.huobi.common.output.Outputer;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 15:38
 */
public class EmailOutputer extends AbstractStringQueueOutputer implements Outputer<String> {

    @Override
    protected void doOutput(String item) {
        HareInvokeUtils.invokeWithTurnRe(() -> {
            Mails.send(item);
            return null;
        });
    }

}
