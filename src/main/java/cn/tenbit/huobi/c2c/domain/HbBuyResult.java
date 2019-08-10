package cn.tenbit.huobi.c2c.domain;

import cn.tenbit.huobi.common.domain.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:20
 */
@Getter
@Setter
@NoArgsConstructor
public class HbBuyResult extends Result<List<HbBuyData>> {
    private static final long serialVersionUID = 2605371828082885040L;

    private Integer currPage;

    private Integer pageSize;

    private Integer totalCount;

    private Integer totalPage;
}
