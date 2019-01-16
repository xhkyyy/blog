package binary_integer;

import java.util.Stack;

public class NumberToBinary {

    public static String method1(int originalNum) {
        int num = originalNum;
        if (num < 0) {
            num = ((num * -1) ^ Integer.MAX_VALUE) + 1;
        }

        int i = num;

        Stack<Integer> stack = new Stack<>();

        while (i > 0) {
            stack.push(i & 1);
            i = i >> 1;
        }

        StringBuilder sb = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        if (originalNum < 0 && sb.length() < 32) {
            while (32 - sb.length() > 0) {
                sb.insert(0, "1");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 456909876;
        String binaryStr = Integer.toBinaryString(num);
        String m1 = method1(num);
        System.out.println(m1);
        System.out.println(binaryStr);
        if (!binaryStr.equals(m1)) {
            throw new IllegalStateException("");
        }

    }
}
