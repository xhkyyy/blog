package com.java87.demo.other;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Len on 10/21/15.
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {

        //1.
        String s = String.join("-", "a", "b", "c");
        System.out.println(s);

        //2.
        System.out.println((Math.floorMod(10, 3)));//替换x % y,来解决x可能为负数的情况处理

        //3.集合类提供了很多新的方法
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("d");
        list1.forEach(System.out::println);

        //4.
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People(10, "a1"));
        peopleList.add(new People(2, "a2"));
        peopleList.add(new People(100, "a3"));
        Collections.sort(peopleList, Comparator.comparing(People::getAge));
        peopleList.forEach(p -> System.out.println(p.getAge()));

        //3.
        try (Stream<String> lines = Files.lines(Paths.get("/Users/Len/Downloads/1.txt.rtf"))){
            lines.forEach(System.out::println);
        }

        //4.原生支持Base64

        //5.注解

        //6.捕获多个异常
        try (Stream<String> lines = Files.lines(new File("/Users/Len/Downloads/1.txt.rtf").toPath())){
            lines.forEach(System.out::println);
            Thread.sleep(100L);
        }catch (FileNotFoundException | InterruptedException e){

        }

        //7.更简单的重写hashCode,equals方法

    }

}
