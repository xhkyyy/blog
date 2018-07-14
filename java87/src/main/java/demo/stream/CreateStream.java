package demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by WY on 2015/10/20.
 */
public class CreateStream {

    public static void main(String[] args) {

        Stream<Integer> numbers1 = Stream.of("1,3,0".split(",")).map(w -> Integer.valueOf(w));
        Stream<Integer> numbers2 = Arrays.asList("1,3,0".split(",")).stream().map(w -> Integer.valueOf(w));

        numbers1.forEach(System.out::println);
        numbers2.forEach(System.out::println);


        Stream<String> list = Stream.generate(() -> "abc");



    }

}
