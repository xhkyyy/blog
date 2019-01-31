package wait_notify.print_num.v1;

import static wait_notify.print_num.PrintNumTest.*;

public class EvenThread extends Thread {

    private final int MAX_NUM;

    public EvenThread(int MAX_NUM) {
        this.MAX_NUM = MAX_NUM;
    }

    @Override
    public void run() {
        int num = 2;

        while (num <= MAX_NUM) {

            // 使用 while 是为了取保执行顺序是先 wait 再 notify, 不能出现还没有 wait 就执行 notify 的情况
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
