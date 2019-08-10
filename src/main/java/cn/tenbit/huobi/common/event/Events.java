package cn.tenbit.huobi.common.event;

import cn.tenbit.hare.core.lite.util.HareAssertUtils;

import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 21:57
 */
public class Events {

    private static class Instance {
        private static final Events INSTANCE = new Events();
    }

    private Events() {
    }

    private final EventDispatcher dispatcher = new EventDispatcher();

    private final EventRegister register = new EventRegister();

    public static Events getInstance() {
        return Instance.INSTANCE;
    }

    public static void register(int type, Listener listener) {
        getInstance().register.register(type, listener);
    }

    public static void register(int type, List<Listener> listeners) {
        HareAssertUtils.notNull(listeners);
        EventRegister register = getInstance().register;
        for (Listener listener : listeners) {
            register.register(type, listener);
        }
    }

    public static void publish(Event event) {
        Events instance = getInstance();
        instance.dispatcher.dispatch(instance.register, event);
    }
}
