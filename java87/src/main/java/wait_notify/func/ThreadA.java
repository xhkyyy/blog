package wait_notify.func;

import java.util.concurrent.TimeUnit;

import static wait_notify.func.TestWait.*;

public class ThreadA extends Thread {
    @Override
    public void run() {

        while (!updated) {
            try {
                System.out.println("休眠");
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (lock) {
            try {
                System.out.println("上锁");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("解锁");

    }
}
