package wait_notify.print_num.v1;


import static wait_notify.print_num.PrintNumTest.*;


public class OddThread extends Thread {

    private final int MAX_NUM;

    public OddThread(int MAX_NUM) {
        this.MAX_NUM = MAX_NUM;
    }

    @Override
    public void run() {
        int num = 1;

        while (num <= MAX_NUM) {

            while (updated) {
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


            while (!updated) {
                synchronized (lock1) {
                    lock1.notify();
                }
            }

            num += 2;


        }

    }
}
