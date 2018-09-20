package jmh;

public class Test {
    public static void main(String[] args) {

        long t = System.nanoTime();

        long token = 2;

        int count = 0;

        for (long i = token; i > 0; i--) {
            t += (t * 0x5DEECE66DL + 0xBL + i) & (0xFFFFFFFFFFFFL);
            count++;
        }

        System.out.println(count);

    }
}
