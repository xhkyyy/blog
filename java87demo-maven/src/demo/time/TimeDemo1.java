package demo.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by WY on 2015/10/21.
 */
public class TimeDemo1 {

    public static void main(String[] args) {

        //1.����2����֮���ʱ��
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
