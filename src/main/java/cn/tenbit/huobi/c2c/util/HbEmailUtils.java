package cn.tenbit.huobi.c2c.util;

import cn.tenbit.hare.core.lite.util.HareTimeUtils;
import cn.tenbit.huobi.c2c.data.HbDatas;
import cn.tenbit.huobi.c2c.event.HbEventType;
import cn.tenbit.huobi.c2c.logic.HbLogic;
import cn.tenbit.huobi.common.event.Events;
import cn.tenbit.huobi.common.event.StringEvent;
import cn.tenbit.huobi.config.ConfigDefine;
import cn.tenbit.huobi.config.Configs;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 20:52
 */
public class HbEmailUtils {

    public static void sendEmailIfNecessary() {
        String property = Configs.getProperty(ConfigDefine.HUOBI_C2C_PRICE_DELTA);
        if (StringUtils.isBlank(property)) {
            property = "0.02";
        }
        BigDecimal deltaPrice = new BigDecimal(property);
        if (HbDatas.getLastBuyPrice() != null && HbDatas.getBuyPrice() != null &&
                HbDatas.getLastBuyPrice().subtract(HbDatas.getBuyPrice()).abs().compareTo(deltaPrice) < 0) {
            return;
        }
        if (HbDatas.getLastSellPrice() != null && HbDatas.getSellPrice() != null &&
                HbDatas.getLastSellPrice().subtract(HbDatas.getSellPrice()).abs().compareTo(deltaPrice) < 0) {
            return;
        }
        if (HbDatas.getLastEmailTime() == null
                || HareTimeUtils.currentTimeMs() - HbDatas.getLastEmailTime() >= ConfigDefine.MIN_EMAIL_INTERVAL_TIME_MS) {
            HbDatas.refreshLastEmailTime();
            Events.publish(new StringEvent(HbEventType.EMAIL, HbLogic.buildEmail()));
        }
    }
}
