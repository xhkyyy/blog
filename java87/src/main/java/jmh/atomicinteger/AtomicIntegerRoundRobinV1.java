package jmh.atomicinteger;

import com.google.common.base.Preconditions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环计数器
 */
public class AtomicIntegerRoundRobinV1 {

    private final int MAX_VALUE;

    private final AtomicInteger COUNTER = new AtomicInteger(-1);

    public AtomicIntegerRoundRobinV1(int MAX_VALUE) {
        Preconditions.checkArgument(MAX_VALUE > 0);
        this.MAX_VALUE = MAX_VALUE - 1;
    }

    public int get() {
        int current;
        int next;

        do {
            current = COUNTER.get();
            next = current >= MAX_VALUE ? 0 : current + 1;
        } while (!COUNTER.compareAndSet(current, next));

        return next;
    }

}
