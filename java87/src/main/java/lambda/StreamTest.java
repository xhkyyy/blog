package lambda;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 使用静态倒入 Lambda Functional 方法对程序的可读性非常好
 */
import static java.util.stream.Collectors.*;

/**
 * 需要 java 9+ 运行
 */
public class StreamTest {

    public static void main(String[] args) {



        /*

        ⚠️注意：
        在 stream 里不要使用下面这个实现方式：

        1）因为他们还是一个和传统 for-loop 类似的四不像实现，
        它最不好的一点就是使用了 forEach 方法，使用这个方法限制了 stream 的并行能力。
        forEach 最好用在 2 个场景中，一个是一个个的输出结果的场景，再就是向一个已经存在的集合中添加 stream 的计算结果

        2）在整个 stream 期间，影响 Stream 的只有输入，不应该有中间的操作会影响 stream，要保证整个 stream
        过程外界无法干扰，也即是一个 immutable 的。

        Map<String, Long> freq = new HashMap<>();
        try(Stream<String> words = new Scanner("").tokens()){
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }
        */


        /*

        👍推荐实现方式
        1）freq 即可在整个 Stream 过程中都是外界无法干扰的，整个过程对外界都是 immutable 的。
        2）抛弃 forEach 更好的利用 Stream 的并行是

        Map<String, Long> freq;
        try (Stream<String> words = new Scanner("").tokens()) {
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        */

    }


}
