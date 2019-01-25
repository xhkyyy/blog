package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * flatMap: 将多个 Stream 合并成一个
 */
public class FlatMapTest {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);

        List<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(5);
        b.add(6);

        System.out.println(Stream.of(a, b).count());

        List<Integer> figures = Stream.of(a, b)
                .flatMap(u -> {
                    System.out.println("\n--- 每一个 List ----");
                    System.out.println(u);
                    System.out.println("-------\n");
                    return u.stream();
                }).collect(Collectors.toList());

        figures.forEach(System.out::println);

    }
}
