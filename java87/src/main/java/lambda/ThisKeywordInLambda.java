package lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在 Lambda 中，`this` 关键词指的是包装它的类的 this，而不是 Lambda 本身
 */
public class ThisKeywordInLambda {

    private int ok = 1;

    public void sort(List<String> l) {
        Collections.sort(l, (x, y) -> {

            // `this` 引用的是所在类的，而不是 Lambda 本身
            System.out.println(this.ok);
            return Integer.compare(x.length(), y.length());
        });
    }

    public static void main(String[] args) {

        List<String> l = new ArrayList<>();

        l.add("hello");
        l.add("hi");

        new ThisKeywordInLambda().sort(l);

        System.out.println(l);

    }

}
