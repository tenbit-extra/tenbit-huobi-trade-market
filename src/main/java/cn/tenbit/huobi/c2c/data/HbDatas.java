package cn.tenbit.huobi.c2c.data;

import cn.tenbit.huobi.config.ConfigDefine;

import java.math.BigDecimal;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:23
 */
public class HbDatas {

    public static void setSellPrice(BigDecimal price) {
        HbDatabase.save(ConfigDefine.DB_KEY_HB_SELL_PRICE, price);
    }

    public static void setBuyPrice(BigDecimal price) {
        HbDatabase.save(ConfigDefine.DB_KEY_HB_BUY_PRICE, price);
    }

    public static BigDecimal getSellPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_SELL_PRICE);
    }

    public static BigDecimal getBuyPrice() {
        return HbDatabase.get(ConfigDefine.DB_KEY_HB_BUY_PRICE);
    }
}
