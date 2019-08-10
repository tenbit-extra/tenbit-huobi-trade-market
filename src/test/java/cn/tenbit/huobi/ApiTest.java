package cn.tenbit.huobi;

import cn.tenbit.hare.core.lite.util.HarePrintUtils;
import cn.tenbit.huobi.c2c.api.HbBuyApi;
import cn.tenbit.huobi.c2c.api.HbSellApi;
import org.junit.Test;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 13:35
 */
public class ApiTest {

    @Test
    public void sellTest() {
        HarePrintUtils.prettyJsonConsole(new HbSellApi("https://otc-api-sz.eiijo.cn/v1/data/trade-market?coinId=2&currency=1&tradeType=sell&currPage=1&payMethod=0&country=37&blockType=general&online=1&range=0&amount=").call(null));
    }

    @Test
    public void buyTest() {
        HarePrintUtils.prettyJsonConsole(new HbBuyApi("https://otc-api-sz.eiijo.cn/v1/data/trade-market?coinId=2&currency=1&tradeType=buy&currPage=1&payMethod=0&country=37&blockType=general&online=1&range=0&amount=").call(null));
    }
}
