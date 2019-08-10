package cn.tenbit.huobi.common.event;

import cn.tenbit.hare.core.lite.util.HareObjectUtils;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 22:51
 */
public class EventRegister {

    private final Map<Integer, List<Listener>> observers = new HashMap<>();

    public void register(int type, Listener listener) {
        List<Listener> listeners = observers.get(type);
        if (listeners == null) {
            observers.putIfAbsent(type, Lists.newArrayList());
            listeners = observers.get(type);
        }
        listeners.add(listener);
    }

    public List<Listener> get(int type) {
        return HareObjectUtils.defaultIfNull(observers.get(type), Collections.emptyList());
    }
}
