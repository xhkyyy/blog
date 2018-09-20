package jmh;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random r = new Random();

        int helloworld = r.nextInt();

        helloworld--;


        System.out.println(helloworld);

    }
}
