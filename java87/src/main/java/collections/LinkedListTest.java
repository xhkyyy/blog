package collections;

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("hi");

        linkedList.add(1, "world");

        System.out.println(linkedList.get(1));


    }
}
