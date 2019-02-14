package multi_threads.atomic_start_once;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class RWThread extends Thread {

    public static final AtomicBoolean ab = new AtomicBoolean(false);

    public static final AtomicInteger count = new AtomicInteger(0);


    @Override
    public void run() {

        System.out.println("Thread ID: " + Thread.currentThread().getId());

        // 多线程环境下，保证仅且只执行一次
        if (!ab.compareAndSet(false, true)) {
            return;
        }

        count.incrementAndGet();
    }
}
