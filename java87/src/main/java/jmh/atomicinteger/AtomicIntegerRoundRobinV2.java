package jmh.atomicinteger;

import com.google.common.base.Preconditions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环计数器
 */
public class AtomicIntegerRoundRobinV2 {

    private final int MAX_VALUE;

    private final AtomicInteger COUNTER = new AtomicInteger(-1);

    private static final int MAX_COUNTER = Integer.MAX_VALUE - 10000;

    public AtomicIntegerRoundRobinV2(int MAX_VALUE) {
        Preconditions.checkArgument(MAX_VALUE > 0);
        this.MAX_VALUE = MAX_VALUE;
    }

    public int get() {
        int current;
        int next;

        do {
            current = COUNTER.get();
            next = current < MAX_COUNTER ? current + 1 : 0;
        } while (!COUNTER.compareAndSet(current, next));

        return next % MAX_VALUE;
    }

}
