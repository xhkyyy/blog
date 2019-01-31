package wait_notify.print_num;

import wait_notify.print_num.v1.EvenThread;
import wait_notify.print_num.v1.OddThread;

public class PrintNumTest {

    public static volatile boolean updated = false;

    public static final Object lock1 = new Object();


    public static void main(String[] args) throws InterruptedException {

        final int MAX = 1000;

        EvenThread evenThread = new EvenThread(MAX);
        evenThread.start();


        OddThread oddThread = new OddThread(MAX);
        oddThread.start();

        evenThread.join();
        oddThread.join();
        System.out.println("-----over------");
    }
}
