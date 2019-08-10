package cn.tenbit.huobi.common.http;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:09
 */
public class OkHttp3s {

    private static class Instance {
        private static final OkHttp3s INSTANCE = new OkHttp3s();
    }

    private OkHttp3s() {
    }

    public static OkHttp3s getInstance() {
        return Instance.INSTANCE;
    }

    private final OkHttpClient client = newInsideClient();

    private static OkHttpClient newInsideClient() {
        return newClientBuilder()
                .addInterceptor(new LogInterceptor())
                .connectionPool(new ConnectionPool())
                .build();
    }

    public static OkHttpClient getClient() {
        return getInstance().client;
    }

    public static OkHttpClient newClient() {
        return new OkHttpClient();
    }

    public static OkHttpClient.Builder newClientBuilder() {
        return newClient().newBuilder();
    }

    public static Request.Builder newRequestBuilder() {
        return new Request.Builder();
    }
}
