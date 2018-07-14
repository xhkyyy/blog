package demo.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by WY on 2015/10/21.
 */
public class CurrentMax_1_2 {

    public static AtomicLong atomicLong = new AtomicLong();

    //1.
    public static void max1(int value){
        atomicLong.updateAndGet(x -> Math.max(x, value));
    }

    //2.
    public static void max2(int value){
        atomicLong.accumulateAndGet(value, Math::max);
    }


    public static void main(String[] args) {

    }

}
