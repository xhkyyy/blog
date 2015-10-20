package com.java87.demo;


import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Objects;

/**
 * Created by WY on 2015/10/20.
 */
public class Lambda_2 {


    public static void main(String[] args) {

        List<String> fruits = Arrays.asList(new String[]{"apple", "orange", "pear"});

        fruits.forEach((e) -> System.out.println(e));

        fruits.stream().map((e) -> Objects.isNull(e) ? "" : e.toUpperCase()).forEach((e) -> System.out.println(e));
        fruits.stream().map((e) -> Objects.isNull(e) ? "" : e.toUpperCase()).forEach(System.out::println);
        List<String> newList = fruits.stream().map((e) -> Objects.isNull(e) ? "" : e.toUpperCase()).collect(Collectors.toList());

        fruits.forEach(System.out::println);

        newList.forEach(System.out::println);

    }

}
