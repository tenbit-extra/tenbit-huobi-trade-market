package cn.tenbit.huobi.c2c.output;

import cn.tenbit.hare.core.lite.function.HareExecutor;
import cn.tenbit.hare.core.lite.util.HareInvokeUtils;
import cn.tenbit.huobi.common.output.Outputer;
import cn.tenbit.huobi.common.threadpool.ThreadPools;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 15:55
 */
public abstract class AbstractStringQueueOutputer implements Outputer<String> {

    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    private final Lock lock = new ReentrantLock();

    private final Condition cond = lock.newCondition();

    public AbstractStringQueueOutputer() {
        initConsumer();
    }

    private void initConsumer() {
        ThreadPools.getPool().submit(() -> {
            while (true) {
                String item = take();
                if (item == null) {
                    continue;
                }
                HareInvokeUtils.invokeWithSwallow(() -> {
                    doOutput(item);
                    return null;
                });
            }
        });
    }

    protected abstract void doOutput(String item);

    @Override
    public void put(String item) {
        if (queue.offer(item)) {
            signal();
        }
    }

    private String take() {
        while (true) {
            String poll = queue.poll();
            if (poll == null) {
                await();
            }
            return poll;
        }
    }

    private void await() {
        doWithLock(() -> {
            cond.await();
            return null;
        });
    }

    private void signal() {
        doWithLock(() -> {
            cond.signalAll();
            return null;
        });
    }

    private void doWithLock(HareExecutor f) {
        HareInvokeUtils.invokeWithTurnRe(() -> {
            try {
                lock.lock();
                f.execute();
            } finally {
                lock.unlock();
            }
            return null;
        });
    }
}
