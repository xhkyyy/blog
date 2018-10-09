package collections;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {

        CopyOnWriteArrayList cowal = new CopyOnWriteArrayList();

        cowal.add("x");

        cowal.add("x1");

        cowal.get(2);


        System.out.println(cowal);

    }
}
