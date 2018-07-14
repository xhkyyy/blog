package demo.concurrent;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * Created by WY on 2015/10/21.
 */
public class CurrentDemo1 {

    public static void main(String[] args) {

        //操作类型,初始值
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

        //多线程中
        longAccumulator.accumulate(10);

        long la = longAccumulator.get();


    }

}
