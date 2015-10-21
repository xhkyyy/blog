package com.java87.demo.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by WY on 2015/10/21.
 */
public class TimeDemo1 {

    public static void main(String[] args) {

        //1.计算2个点之间的时间
        Instant start = Instant.now();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant end = Instant.now();

        System.out.println("start - end = " + Duration.between(start, end).toMillis() + "ms");


    }

}
