package demo.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Created by WY on 2015/10/21.
 */
public class TimeDemo3 {

    public static void main(String[] args) {

        //1.���������ʱ��
        LocalDate t1 = LocalDate.now();
        LocalDate hny = LocalDate.of(2016, 1, 1);
        System.out.println(t1.until(hny, ChronoUnit.DAYS));



    }

}
