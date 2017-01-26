package com.java87.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Len on 10/20/15.
 */
public class StreamDemo2 {

    public static void main(String[] args) {

        List<String> fruits = Arrays.asList(new String[]{"apple", "orange", "pear"});

        //1.
        Stream<String> fruitStream = fruits.stream().filter(w -> w.length() > 4);

        fruitStream.forEach(System.out::println);

        //2.
        fruits.stream().map(w -> w + "_" + "0").forEach(System.out::println);


        //3.1 Java 8以前
        System.out.println("\n\nJava 8以前");
        List<List<Integer>> integerLists = Arrays.asList(
                Arrays.asList(99, 1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );

        List<Integer> flattened = new ArrayList<>();

        for (List<Integer> integerList : integerLists) {
            flattened.addAll(integerList);
        }

        for (Integer i : flattened) {
            System.out.println(i);
        }


        //flatMap会把子Stream的元素压缩到父集合中
        System.out.println("\n\nJava 8");
        Stream<List<Integer>> integerListStream = integerLists.stream();
        Stream<Integer> integerStream = integerListStream.flatMap(Collection::stream);
        integerStream.forEach(System.out::println);


    }

}
