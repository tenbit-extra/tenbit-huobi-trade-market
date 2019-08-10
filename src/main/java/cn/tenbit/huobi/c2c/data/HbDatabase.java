package cn.tenbit.huobi.c2c.data;

import cn.tenbit.hare.core.lite.constant.HareConsts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:02
 */
public class HbDatabase {

    private static class Instance {
        private static final HbDatabase INSTANCE = new HbDatabase();
    }

    private HbDatabase() {
    }

    public static HbDatabase getInstance() {
        return Instance.INSTANCE;
    }

    private final Map<String, Object> db = new ConcurrentHashMap<>();

    public static void save(String key, Object data) {
        if (key == null || data == null) {
            return;
        }
        getInstance().db.put(key, data);
    }

    @SuppressWarnings(HareConsts.SUPPRESS_WARNING_UNCHECKED)
    public static <T> T get(String key) {
        return (T) getInstance().db.get(key);
    }
}
