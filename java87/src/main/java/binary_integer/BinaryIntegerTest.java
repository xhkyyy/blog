package binary_integer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 获取一个数的 2 的 N 次方
 */
public class BinaryIntegerTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test1() {

        int mask = Integer.parseInt("10010000", 2);

        mask |= mask >>> 1;
        mask |= mask >>> 2;
        mask |= mask >>> 4;

        System.out.println(Integer.toBinaryString(mask));

    }

    @Test
    public void test2() {
        int mask = Integer.parseInt("10010000", 2);

        int i = tableSizeFor(mask);

        Assert.assertTrue(i % 2 == 0);

        System.out.println(Integer.toBinaryString(i));
    }

    @Test
    public void test3() {
        for (int n = 2; n < 100000000; n++) {

            int i = tableSizeFor(n);

            try {
                Assert.assertTrue(i % 2 == 0);
            } catch (Throwable e) {
                System.out.println("n: " + n);
                System.out.println("i: " + i);
                System.out.println(Integer.toBinaryString(i));
                throw e;
            }

            // System.out.println("n: " + n + " / " + Integer.toBinaryString(i));
        }
    }

    @Test
    public void test4() {
        for (int n = 2; n < 100000000; n++) {

            // 对于同一个数，两者计算出的结果可能不一样
            int i1 = tableSizeFor2(n);
            int i2 = tableSizeFor(n);

            try {
                Assert.assertTrue(i1 % 2 == 0);
                Assert.assertTrue(i1 == i2);
            } catch (Throwable e) {
                System.out.println("n: " + n);
                System.out.println("i1: " + i1);
                System.out.println("i2: " + i2);
                System.out.println(Integer.toBinaryString(i1));
                throw e;
            }
        }
    }

    /**
     * java 8 hashmap 原生实现
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 自己的实现逻辑
     *
     * @param num
     * @return
     */
    static final int tableSizeFor2(int num) {
        if (num % 2 == 0) {
            return num;
        }
        num |= num >> 1;
        num |= num >> 2;
        num |= num >> 4;
        return num + 1;
    }

    @Test
    public void mainx() {
        System.out.println("\n\n-----------------");
        // 二进制转换为 Integer
        System.out.println(Integer.parseInt("111111111111", 2));
        System.out.println("-----------------\n\n");

    }

    public static class HashMapTableSizeForTest {
    }
}
