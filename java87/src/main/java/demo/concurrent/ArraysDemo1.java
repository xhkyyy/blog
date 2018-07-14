package demo.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by Len on 10/21/15.
 */
public class ArraysDemo1 {

    public static void main(String[] args) {

        int[] i = new int[]{10, 8, 1};

        //并行排序
        Arrays.parallelSort(i);

        for (int i1 : i) {
            System.out.println(i1);
        }

    }

}
