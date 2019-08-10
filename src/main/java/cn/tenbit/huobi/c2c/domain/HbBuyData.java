package cn.tenbit.huobi.c2c.domain;

import cn.tenbit.huobi.common.domain.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:27
 */
@Getter
@Setter
@NoArgsConstructor
public class HbBuyData extends Data {
    private static final long serialVersionUID = 7049292546692468424L;

    private Long id;

    private Long uid;

    private String userName;

    private Long merchantLevel;

    private List<String> merchantTags;

    private Long coinId;

    private Long currency;

    private Long tradeType;

    private Long blockType;

    private String payMethod;

    private Long payTerm;

    private String payName;

    private Long mLongradeLimit;

    private Long maxTradeLimit;

    private BigDecimal price;

    private BigDecimal tradeCount;

    private Boolean isOnline;

    private Long tradeMonthTimes;

    private Long orderCompleteRate;

    private Long takerLimit;

    private Long gmtSort;
}
