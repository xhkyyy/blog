package lambda;

import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class ComparingReversedTest {
    public static void main(String[] args) {

        Map<String, Long> freq = new HashMap<>();

        freq.put("world", 2L);
        freq.put("hi", 1L);
        freq.put("ZEN", 3L);

        System.out.println(
                freq
                        .keySet()
                        .stream()
                        .sorted(
                                comparing(freq::get).reversed()
                        )
                        .limit(10)
                        .collect(toList())
        );

    }
}
