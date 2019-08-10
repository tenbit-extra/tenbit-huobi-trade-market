package cn.tenbit.huobi.c2c.watch;

import cn.tenbit.huobi.c2c.domain.HbSellResult;
import cn.tenbit.huobi.c2c.util.HbWatchUtils;
import cn.tenbit.huobi.common.analyse.Analyser;
import cn.tenbit.huobi.common.input.Inputer;
import cn.tenbit.huobi.common.watch.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 14:05
 */
public class HbSellWatcher implements Watcher<HbSellResult> {

    private Inputer<HbSellResult> inputer;

    private final List<Analyser<HbSellResult>> analysers = new ArrayList<>();

    @Override
    public void work() {
        HbWatchUtils.watch(this);
    }

    @Override
    public void setInputer(Inputer<HbSellResult> inputer) {
        this.inputer = inputer;
    }

    @Override
    public Inputer<HbSellResult> getInputer() {
        return inputer;
    }

    @Override
    public List<Analyser<HbSellResult>> getAnalysers() {
        return analysers;
    }

    @Override
    public void addAnalyser(Analyser<HbSellResult> analyser) {
        analysers.add(analyser);
    }
}
