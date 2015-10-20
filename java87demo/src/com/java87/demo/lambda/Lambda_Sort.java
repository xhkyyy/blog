package com.java87.demo.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by WY on 2015/10/20.
 */
public class Lambda_Sort {


    public static void main(String[] args) {

        //1-方法引用
       String[] fruits = new String[]{"apple", "ab", "orange", "pear", "ab"};

        //Arrays.sort(fruits, (String first, String second) -> Integer.compare(first.length(), second.length()));
        Arrays.sort(fruits, (first, second) -> Integer.compare(first.length(), second.length()));

        Arrays.asList(fruits).forEach(System.out::println);


        //2-方法引用
        Arrays.sort(fruits, String::compareToIgnoreCase);

        Arrays.asList(fruits).forEach(System.out::println);

        //3-构造函数引用(使用list中的字符串来构造一个Integer)
        List<String> nums = new ArrayList<String>(2);
        nums.add("1");
        nums.add("9");
        nums.stream()
                .map(Integer::new)
                .collect(Collectors.toList())
                .forEach((v) -> {
                    System.out.println(v + " : " + v.getClass().getName());
                });

    }

}
