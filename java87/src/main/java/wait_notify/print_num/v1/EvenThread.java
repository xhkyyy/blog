package wait_notify.print_num.v1;

import static wait_notify.print_num.PrintNumTest.*;

public class EvenThread extends Thread {
    @Override
    public void run() {
        int num = 2;

        while (num <= 100) {

            while (updated) {
                synchronized (lock1) {
                    lock1.notify();
                }
            }

            while (!updated) {
                synchronized (lock1) {
                    try {
                        lock1.wait();
                        updated = !updated;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            System.out.println(num);
            num += 2;

        }

    }
}
