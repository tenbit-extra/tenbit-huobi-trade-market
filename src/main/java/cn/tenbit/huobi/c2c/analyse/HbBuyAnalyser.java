package cn.tenbit.huobi.c2c.analyse;

import cn.tenbit.huobi.c2c.data.HbDatas;
import cn.tenbit.huobi.c2c.domain.HbBuyData;
import cn.tenbit.huobi.c2c.domain.HbBuyResult;
import cn.tenbit.huobi.c2c.event.HbEventType;
import cn.tenbit.huobi.c2c.logic.HbLogic;
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
public class HbBuyAnalyser implements Analyser<HbBuyResult> {

    @Override
    public void analyse(HbBuyResult result) {
        if (result == null) {
            return;
        }

        List<HbBuyData> datas = result.getData();
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }

        HbBuyData data = datas.get(0);
        BigDecimal price = data.getPrice();
        if (price == null) {
            return;
        }

        BigDecimal dbPrice = HbDatas.getBuyPrice();

        if (dbPrice == null || dbPrice.compareTo(price) != 0) {
            HbDatas.setBuyPrice(price);
            Events.publish(new StringEvent(HbEventType.EMAIL, HbLogic.buildEmail()));
            Events.publish(new StringEvent(HbEventType.CONSOLE, HbLogic.buildConsoleForBuyPrice()));
        }

        Events.publish(new StringEvent(HbEventType.CONSOLE, HbLogic.buildConsole()));
    }
}
