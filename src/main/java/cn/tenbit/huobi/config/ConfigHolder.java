package cn.tenbit.huobi.config;

import cn.tenbit.hare.core.lite.util.HareAssertUtils;
import cn.tenbit.hare.core.lite.util.HareStringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Properties;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:52
 */
public class ConfigHolder {

    private final JSONObject prop = new JSONObject();

    public void put(String key, String val) {
        prop.put(StringUtils.trim(key), StringUtils.trim(val));
    }

    public void putAll(Map<String, String> map) {
        if (MapUtils.isEmpty(map)) {
            return;
        }
        for (String key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    public void putAll(Properties prop) {
        HareAssertUtils.notNull(prop);
        for (Object key : prop.keySet()) {
            put(HareStringUtils.toNotNullString(key), HareStringUtils.toNotNullString(prop.get(key)));
        }
    }

    public String getString(String key) {
        return prop.getString(StringUtils.trim(key));
    }

    public JSONObject getProp() {
        return prop;
    }
}
