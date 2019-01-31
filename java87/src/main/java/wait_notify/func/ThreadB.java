package wait_notify.func;

import static wait_notify.func.TestWait.*;

public class ThreadB extends Thread {
    @Override
    public void run() {

        synchronized (lock) {
            lock.notify();
            System.out.println("notify");
        }

        updated = true;


    }
}
