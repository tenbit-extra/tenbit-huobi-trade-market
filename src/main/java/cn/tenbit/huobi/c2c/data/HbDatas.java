package cn.tenbit.huobi.c2c.data;

import cn.tenbit.hare.core.lite.util.HareTimeUtils;
import cn.tenbit.huobi.config.ConfigDefine;

import java.math.BigDecimal;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:23
 */
public class HbDatas {

    public static void setLastSellPrice(BigDecimal price) {
        HbDatabase.save(ConfigDefine.DB_KEY_HB_LAST_SELL_PRICE, price);
    }

    public static void setLastBuyPrice(BigDecimal price) {
        HbDatabase.save(ConfigDefine.DB_KEY_HB_LAST_BUY_PRICE, price);
    }

    public static BigDecimal getLastSellPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_LAST_SELL_PRICE);
    }

    public static BigDecimal getLastBuyPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_LAST_BUY_PRICE);
    }

    public static void setSellPrice(BigDecimal price) {
        setLastSellPrice(HbDatas.getSellPrice());
        HbDatabase.save(ConfigDefine.DB_KEY_HB_SELL_PRICE, price);
    }

    public static void setBuyPrice(BigDecimal price) {
        setLastBuyPrice(HbDatas.getBuyPrice());
        HbDatabase.save(ConfigDefine.DB_KEY_HB_BUY_PRICE, price);
    }

    public static BigDecimal getSellPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_SELL_PRICE);
    }

    public static BigDecimal getBuyPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_BUY_PRICE);
    }

    public static void refreshLastEmailTime() {
        HbDatabase.save(ConfigDefine.DB_KEY_HB_EMAIL_LAST_SEND_TIME, HareTimeUtils.currentTimeMs());
    }

    public static Long getLastEmailTime() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_EMAIL_LAST_SEND_TIME);
    }
}
