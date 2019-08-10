package cn.tenbit.huobi.common.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 14:43
 */
public class ThreadPools {

    private static class Instance {
        private static final ThreadPools INSATNCE = new ThreadPools();
    }

    private ThreadPools() {
    }

    private final ExecutorService pool = Executors.newCachedThreadPool();

    public static ThreadPools getInstance() {
        return Instance.INSATNCE;
    }

    public static ExecutorService getPool() {
        return getInstance().pool;
    }
}
