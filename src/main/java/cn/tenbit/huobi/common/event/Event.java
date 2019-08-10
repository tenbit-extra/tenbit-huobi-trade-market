package cn.tenbit.huobi.common.event;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 22:53
 */
public interface Event<T> {

    T getData();

    int getType();
}
