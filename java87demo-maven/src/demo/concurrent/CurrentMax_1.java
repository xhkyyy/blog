package com.java87.demo.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by WY on 2015/10/21.
 */
public class CurrentMax_1 {

    public static AtomicLong atomicLong = new AtomicLong();

    public static void max(int value){
        long oldVal;
        long newVal;

        do {
            oldVal = atomicLong.get();
            newVal = Math.max(oldVal, value);
        } while (!atomicLong.compareAndSet(oldVal, newVal));
    }


    public static void main(String[] args) {



    }

}
