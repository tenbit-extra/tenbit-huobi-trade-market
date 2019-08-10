package cn.tenbit.huobi.c2c.input;

import cn.tenbit.huobi.c2c.api.HbSellApi;
import cn.tenbit.huobi.c2c.domain.HbSellResult;
import cn.tenbit.huobi.common.api.Api;
import cn.tenbit.huobi.common.input.Inputer;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 13:42
 */
public class HbSellInputer implements Inputer<HbSellResult> {

    private Api<Void, HbSellResult> api;

    public HbSellInputer(String api) {
        this.api = new HbSellApi(api);
    }

    @Override
    public HbSellResult get() {
        return api.call(null);
    }
}
