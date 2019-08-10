package cn.tenbit.huobi.common.output;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 13:43
 */
public interface Outputer<T> {

    void put(T t);
}
