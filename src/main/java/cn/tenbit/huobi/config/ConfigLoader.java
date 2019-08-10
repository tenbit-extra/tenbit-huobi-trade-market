package cn.tenbit.huobi.config;

import cn.tenbit.hare.core.lite.util.HarePropertiesUtils;

import java.io.File;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:53
 */
public class ConfigLoader {

    public void reload(ConfigHolder holder) {
        holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.CONFIG_PROPERTIES));
        holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.MAIL_PROPERTIES));

        if (checkFileExists(ConfigDefine.CONFIG_OUT_PROPERTIES)) {
            holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.CONFIG_PROPERTIES));
        }
        if (checkFileExists(ConfigDefine.MAIL_OUT_PROPERTIES)) {
            holder.putAll(HarePropertiesUtils.loadFromPropertiesFile(ConfigDefine.CONFIG_PROPERTIES));
        }
    }

    private boolean checkFileExists(String path) {
        File file = new File(path);
        return file.isFile() && file.exists();
    }
}
