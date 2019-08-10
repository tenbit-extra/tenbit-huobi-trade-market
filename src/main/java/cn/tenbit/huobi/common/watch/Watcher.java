package cn.tenbit.huobi.common.watch;

import cn.tenbit.huobi.common.analyse.Analyser;
import cn.tenbit.huobi.common.input.Inputer;

import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 14:05
 */
public interface Watcher<T> {

    void work();

    void setInputer(Inputer<T> inputer);

    Inputer<T> getInputer();

    List<Analyser<T>> getAnalysers();

    void addAnalyser(Analyser<T> analyser);
}
