package cn.tenbit.huobi.c2c.api;

import cn.tenbit.huobi.c2c.domain.HbBuyResult;
import cn.tenbit.huobi.c2c.util.HbApiUtils;
import cn.tenbit.huobi.common.api.Api;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:18
 */
public class HbBuyApi implements Api<Void, HbBuyResult> {

    private String api;

    public HbBuyApi(String api) {
        this.api = api;
    }

    @Override
    public HbBuyResult call(Void param) {
        return HbApiUtils.get(api, HbBuyResult.class);
    }
}
