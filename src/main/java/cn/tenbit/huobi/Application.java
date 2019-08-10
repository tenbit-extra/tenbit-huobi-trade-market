package cn.tenbit.huobi;

import cn.tenbit.huobi.c2c.invoke.HbInvoker;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 15:30
 */
public class Application {

    public static void main(String[] args) {
        HbInvoker invoker = new HbInvoker();
        invoker.work();
    }
}
