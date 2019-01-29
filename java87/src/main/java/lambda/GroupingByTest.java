package lambda;

import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class GroupingByTest {
    public static void main(String[] args) {

        String[] values = "a,b,a,a,c,b".split(",");

        Map<String, Long> m = Stream.of(values).collect(groupingBy(s -> s, counting()));
        System.out.println(m);

        m = Stream.of(values).collect(groupingBy(String::toString, counting()));
        System.out.println(m);

        m = Stream.of(values).collect(groupingBy(String::toLowerCase, counting()));
        System.out.println(m);

    }
}
