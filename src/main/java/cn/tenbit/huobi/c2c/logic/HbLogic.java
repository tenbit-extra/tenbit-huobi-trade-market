package cn.tenbit.huobi.c2c.logic;

import cn.tenbit.huobi.c2c.data.HbDatas;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:12
 */
public class HbLogic {

    public static String buildEmail() {
        return "Latest Sell Price: " + HbDatas.getBuyPrice() + ", Latest Buy Price: " + HbDatas.getSellPrice();
    }

    public static String buildConsoleForBuyPrice() {
        return "卖价升高，最新卖价：" + HbDatas.getBuyPrice();
    }

    public static String buildConsoleForSellPrice() {
        return "买价降低，最新买价：" + HbDatas.getSellPrice();
    }

    public static String buildConsole() {
        return null;
    }
}
