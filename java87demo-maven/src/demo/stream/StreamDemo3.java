package com.java87.demo.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Len on 10/20/15.
 */
public class StreamDemo3 {

    public static Stream<Integer> initList(){
        return Arrays.asList("0,8,4,0,1".split(",")).stream().map(v -> Integer.valueOf(v));
    }

    public static void main(String[] args) {

        //1.聚合,找出最大值
        Stream<Integer> maxStream = initList();

        Optional<Integer> maxVal = maxStream.max(Integer::compare);

        if(maxVal.isPresent()){
            System.out.println("max : " + maxVal.get());
        }

        //2.find first
        Stream<Integer> firstStream = initList();
        Optional<Integer> firstVal = firstStream.findFirst();

        if(firstVal.isPresent()){
            System.out.println("find first : " + firstVal.get());
        }




    }

}
