package cn.tenbit.huobi.c2c.event;

import cn.tenbit.huobi.c2c.output.EmailOutputer;
import cn.tenbit.huobi.common.event.Event;
import cn.tenbit.huobi.common.event.Listener;
import cn.tenbit.huobi.common.output.Outputer;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 15:35
 */
public class EmailOutputListener implements Listener<String> {

    private final Outputer<String> outputer = new EmailOutputer();

    @Override
    public void onChanged(Event<String> e) {
        String data = e.getData();
        if (StringUtils.isBlank(data)) {
            return;
        }
        outputer.put(data);
    }
}
