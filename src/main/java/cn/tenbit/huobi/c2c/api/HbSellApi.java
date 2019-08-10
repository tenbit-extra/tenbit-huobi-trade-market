package cn.tenbit.huobi.c2c.api;

import cn.tenbit.huobi.c2c.domain.HbSellResult;
import cn.tenbit.huobi.c2c.util.HbApiUtils;
import cn.tenbit.huobi.common.api.Api;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:18
 */
public class HbSellApi implements Api<Void, HbSellResult> {

    private String api;

    public HbSellApi(String api) {
        this.api = api;
    }

    @Override
    public HbSellResult call(Void param) {
        return HbApiUtils.get(api, HbSellResult.class);
    }
}
