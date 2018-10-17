package best_practice.remainder;


import java.util.Random;


public class RemainderTest {

    public static void main(String[] args) {

        Random random = new Random();

        int a = Math.abs(random.nextInt());

        int b = 8;

        int r1 = a % b;
        int r2 = a & (b - 1);
        System.out.println(a);
        System.out.println(b);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r1 == r2);

    }

}
