package cn.tenbit.huobi.common.api;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 11:19
 */
public interface Api<S, R> {

    R call(S s);

}
