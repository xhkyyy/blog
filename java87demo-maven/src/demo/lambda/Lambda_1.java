package demo.lambda;

/**
 * Created by WY on 2015/10/20.
 */
public class Lambda_1 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        System.out.println("Run!");
                    }
                });

        thread.start();

        thread.join();

    }

}
