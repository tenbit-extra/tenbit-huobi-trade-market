package cn.tenbit.huobi.common.http;

import cn.tenbit.hare.core.lite.util.HareStringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:54
 */
@Slf4j
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        log.debug(HareStringUtils.toNotNullString(request));
        Response response = null;
        try {
            response = chain.proceed(request);
        } finally {
            log.debug(HareStringUtils.toNotNullString(response));
        }
        return response;
    }
}
