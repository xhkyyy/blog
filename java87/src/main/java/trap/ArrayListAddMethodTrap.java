package trap;

import java.util.ArrayList;
import java.util.List;

public class ArrayListAddMethodTrap {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add("i" + i);
        }

        // [i0, i1, i2]
        System.out.println(list);

        // 在 0 位置插入新的元素，其后的元素以后向后移动
        list.add(0, "i0-");


        // [i0-, i0, i1, i2]
        System.out.println(list);

    }
}
