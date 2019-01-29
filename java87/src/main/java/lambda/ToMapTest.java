package lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ToMapTest {
    public static void main(String[] args) {

        // 创建一个 Map，它的 key 是 values 的字符串形式，value 是整数
        // key 和 value 都是通过 toMap 的两个 lambda 函数生成的
        String[] values = "1,2,3".split(",");

        Map<String, Integer> m = Stream.of(values).collect(toMap(Object::toString, Integer::valueOf));

        System.out.println(m);

        Map<String, String> m2 = Stream.of(values).collect(toMap(s -> "k-" + s, s -> "v-" + s));

        System.out.println(m2);


    }
}
