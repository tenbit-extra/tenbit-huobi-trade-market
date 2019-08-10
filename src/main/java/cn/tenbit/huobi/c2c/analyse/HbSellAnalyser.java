package cn.tenbit.huobi.c2c.analyse;

import cn.tenbit.huobi.c2c.data.HbDatas;
import cn.tenbit.huobi.c2c.domain.HbSellData;
import cn.tenbit.huobi.c2c.domain.HbSellResult;
import cn.tenbit.huobi.c2c.event.HbEventType;
import cn.tenbit.huobi.c2c.logic.HbLogic;
import cn.tenbit.huobi.c2c.util.HbEmailUtils;
import cn.tenbit.huobi.common.analyse.Analyser;
import cn.tenbit.huobi.common.event.Events;
import cn.tenbit.huobi.common.event.StringEvent;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 13:39
 */
public class HbSellAnalyser implements Analyser<HbSellResult> {

    @Override
    public void analyse(HbSellResult result) {
        if (result == null) {
            return;
        }

        List<HbSellData> datas = result.getData();
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }

        HbSellData data = datas.get(0);
        BigDecimal price = data.getPrice();
        if (price == null) {
            return;
        }

        BigDecimal dbPrice = HbDatas.getSellPrice();

        if (dbPrice == null || dbPrice.compareTo(price) != 0) {
            HbDatas.setSellPrice(price);
            HbEmailUtils.sendEmailIfNecessary();
            Events.publish(new StringEvent(HbEventType.CONSOLE, HbLogic.buildConsoleForSellPrice()));
        }

        Events.publish(new StringEvent(HbEventType.CONSOLE, HbLogic.buildConsole()));
    }
}
