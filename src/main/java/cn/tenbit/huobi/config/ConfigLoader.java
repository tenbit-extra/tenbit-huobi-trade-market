package cn.tenbit.huobi.config;

import cn.tenbit.hare.core.lite.util.HarePropertiesUtils;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:53
 */
public class ConfigLoader {

    public void reload(ConfigHolder holder) {
        holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.CONFIG_PROPERTIES));
        holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.MAIL_PROPERTIES));
    }
}
