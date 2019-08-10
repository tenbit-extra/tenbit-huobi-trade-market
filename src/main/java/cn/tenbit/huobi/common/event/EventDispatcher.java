package cn.tenbit.huobi.common.event;

import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 12:05
 */
public class EventDispatcher {

    public void dispatch(EventRegister register, Event event) {
        List<Listener> listeners = register.get(event.getType());
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        }
        for (Listener listener : listeners) {
            HareInvokeUtils.invokeWithSwallow(() -> {
                listener.onChanged(event);
                return null;
            });
        }
    }
}
