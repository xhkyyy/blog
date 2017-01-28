package demo.lambda;

/**
 * Created by WY on 2015/10/20.
 */
public class Lambda_1_2 {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(
                () -> {
                    System.out.println("Lambda Run");
                });

        thread.start();

        thread.join();

    }

}
