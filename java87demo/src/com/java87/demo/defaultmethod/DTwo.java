package com.java87.demo.defaultmethod;

/**
 * Created by WY on 2015/10/20.
 */
public class DTwo implements DMInterface {
    @Override
    public long getId() {
        return 20;
    }

    @Override
    public String getName() {
        return "Two!";
    }

    public static void main(String[] args) {
        DTwo two = new DTwo();

        System.out.println(two.getName());
    }

}
