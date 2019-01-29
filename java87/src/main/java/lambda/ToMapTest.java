package lambda;

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

        // 使用 toMap 时，当存在多个相同的 key 时，是会报错的，怎么解决呢？
        // 当存在多个相同的 key 时，通过一个 mergeFunction 完成相同 key 下，它们的值和值之间的计算关系
        values = "a,c,b,a,a,a,c".split(",");
        m2 = Stream.of(values).collect(toMap(Object::toString, Object::toString, ((value1, value2) -> value1 + "_" + value2)));
        System.out.println(m2);


    }
}
