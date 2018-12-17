package function;

import java.util.function.Function;

public class Test1 {

    private static Function<Integer, String> getFunc() {
        return (i -> {
            return String.valueOf(i) + "_";
        });
    }

    public static void main(String[] args) {
        System.out.println(getFunc().apply(1998));
    }

}
