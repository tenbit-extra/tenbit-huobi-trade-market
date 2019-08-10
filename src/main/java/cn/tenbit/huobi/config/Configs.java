package cn.tenbit.huobi.config;

import cn.tenbit.hare.core.lite.constant.HareConsts;
import cn.tenbit.hare.core.lite.util.HareJsonUtils;
import cn.tenbit.hare.core.lite.util.HareObjectUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:52
 */
public class Configs {

    private static class Instance {
        private static final Configs INSTANCE = new Configs();
    }

    private Configs() {
    }

    private final ConfigLoader loader = new ConfigLoader();

    private final ConfigHolder holder = new ConfigHolder();

    public static Configs getInstance() {
        return Instance.INSTANCE;
    }

    public static void reloadProperties() {
        Configs instance = getInstance();
        getInstance().loader.reload(instance.holder);
    }

    public static String showProperties() {
        return HareJsonUtils.toJsonString(getInstance().holder.getProp(), SerializerFeature.PrettyFormat);
    }

    @SuppressWarnings(HareConsts.SUPPRESS_WARNING_UNCHECKED)
    public static <T> T getProperty(String key, Class<T> clz) {
        key = StringUtils.trim(key);
        JSONObject prop = getInstance().holder.getProp();
        if (prop.get(key) == null) {
            return null;
        }
        if (HareObjectUtils.equals(String.class, clz)) {
            return (T) StringUtils.trim(prop.getString(key));
        }
        if (HareObjectUtils.equals(Integer.class, clz)) {
            return (T) prop.getInteger(key);
        }
        return prop.getObject(key, clz);
    }

    public static String getProperty(String key) {
        return getProperty(key, String.class);
    }
}
