package wait_notify.func;

public class TestWait {

    public static final Object lock = new Object();

    public static volatile boolean updated = false;


    public static void main(String[] args) throws InterruptedException {

        ThreadA threadA = new ThreadA();

        ThreadB threadB = new ThreadB();

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
    }
}
