package cn.tenbit.huobi.c2c.watch;

import cn.tenbit.huobi.c2c.domain.HbBuyResult;
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
public class HbBuyWatcher implements Watcher<HbBuyResult> {

    private Inputer<HbBuyResult> inputer;

    private final List<Analyser<HbBuyResult>> analysers = new ArrayList<>();

    @Override
    public void work() {
        HbWatchUtils.watch(this);
    }

    @Override
    public void setInputer(Inputer<HbBuyResult> inputer) {
        this.inputer = inputer;
    }

    @Override
    public Inputer<HbBuyResult> getInputer() {
        return inputer;
    }

    @Override
    public List<Analyser<HbBuyResult>> getAnalysers() {
        return analysers;
    }

    @Override
    public void addAnalyser(Analyser<HbBuyResult> analyser) {
        analysers.add(analyser);
    }
}
