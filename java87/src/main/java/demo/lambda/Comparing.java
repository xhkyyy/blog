package demo.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class Comparing {

    private static void fn1(List<String> l) {
        Collections.sort(l, comparingInt(String::length));
        System.out.println(l);
        System.out.println(l);
    }

    /**
     * @param originalList
     */
    private static void fn2(List<String> originalList) {
        ArrayList<String> l = copyList(originalList);
        l.sort(comparingInt(String::length));
        System.out.println(l);
    }

    private static ArrayList<String> copyList(List<String> originalList) {
        return new ArrayList<>(originalList);
    }

    public static void main(String[] args) {

        List<String> originalList = new ArrayList<>();
        originalList.add("hello");
        originalList.add("hi");

        fn1(copyList(originalList));

        fn2(copyList(originalList));
    }

}
