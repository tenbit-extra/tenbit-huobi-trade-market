package cn.tenbit.huobi.c2c.util;

import cn.tenbit.hare.core.lite.util.HareAssertUtils;
import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.hare.core.lite.util.HareJsonUtils;
import cn.tenbit.huobi.common.http.OkHttp3s;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 12:38
 */
public class HbApiUtils {

    public static <T> T get(String url, Class<T> clz) {
        return HareInvokeUtils.invokeWithTurnRe(() -> {
            Request request = OkHttp3s.newRequestBuilder().get().url(url).build();
            Response response = OkHttp3s.getClient().newCall(request).execute();
            ResponseBody responseBody = response.body();
            HareAssertUtils.notNull(responseBody);
            return HareJsonUtils.parseObject(responseBody.string(), clz);
        });
    }
}
