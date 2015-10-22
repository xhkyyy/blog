package com.java87.demo.lambda;


import com.java87.demo.other.People;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by WY on 2015/10/20.
 */
public class LambdaCon {


    public static void main(String[] args) {

        List<String> fruitStrList = Arrays.asList(new String[]{"apple", "orange", "pear"});

        List<Fruit> fruitList = fruitStrList.stream().map(Fruit::new).collect(Collectors.toList());

        fruitList.forEach(f -> System.out.println(f.getName()));


    }

}
