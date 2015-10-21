package com.java87.demo.time;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by WY on 2015/10/21.
 */
public class TimeDemo4 {

    public static void main(String[] args) {

        //1.这个类封装一天中的某个时间，当地理位置不重要的情况下，可以使用这个类来只存储一天当中的某个时间。
        LocalTime t1 = LocalTime.now();

        //2.该类封装了一个年/月/日的组合。当地理位置（即时区）变得不重要时，使用它存储日期将非常方便。
        LocalDate t2 = LocalDate.now();



    }

}
