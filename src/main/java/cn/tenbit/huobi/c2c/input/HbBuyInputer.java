package cn.tenbit.huobi.c2c.input;

import cn.tenbit.huobi.c2c.api.HbBuyApi;
import cn.tenbit.huobi.c2c.domain.HbBuyResult;
import cn.tenbit.huobi.common.api.Api;
import cn.tenbit.huobi.common.input.Inputer;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 13:42
 */
public class HbBuyInputer implements Inputer<HbBuyResult> {

    private Api<Void, HbBuyResult> api;

    public HbBuyInputer(String api) {
        this.api = new HbBuyApi(api);
    }

    @Override
    public HbBuyResult get() {
        return api.call(null);
    }
}
