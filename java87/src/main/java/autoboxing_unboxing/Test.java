package autoboxing_unboxing;

import org.junit.Assert;

/**
 * 解答《深入理解Java虚拟机:JVM高级特性与最佳实践》中关于拆箱与装箱题目
 */
public class Test {

    @org.junit.Test
    public void test() {

        /**
         * Integer 默认缓存了 -125 到 127 之间到 Integer 实例
         */

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        Assert.assertTrue((c == d)); // Integer 和 Integer 比较
        Assert.assertFalse(e == f); // Integer 和 Integer 比较，其中 321 已经超出了 Integer 缓存实例
        Assert.assertTrue(c == (a + b));// 算数运算触发拆箱，int 和 int 比较
        Assert.assertTrue(c.equals(a + b));// a + b 遇到算数运算拆箱，c.equals(Object o) 参数是对象类型，所以触发装箱。 equals 方法中会发生再次拆箱。
        Assert.assertTrue(g == (a + b));// a + b 遇到算数运算拆箱，于是 g 也拆箱

        /*
        a + b 遇到算数运算拆箱，g.equals(Object ) 参数是对象类型，
        所以触发装箱。equals 方法中，由于 (a+b)通过 Integer 装箱，所以不是 Long 类型，因此直接返回 false
         */
        Assert.assertFalse(g.equals(a + b));

        /**
         反编译后的代码：
         Integer a = Integer.valueOf(1);
         Integer b = Integer.valueOf(2);
         Integer c = Integer.valueOf(3);
         Integer d = Integer.valueOf(3);
         Integer e = Integer.valueOf(321);
         Integer f = Integer.valueOf(321);
         Long g = Long.valueOf(3L);
         System.out.println(c == d);
         System.out.println(e == f);
         System.out.println(c.intValue() == a.intValue() + b.intValue());
         System.out.println(c.equals(Integer.valueOf(a.intValue() + b.intValue())));
         System.out.println(g.longValue() == a.intValue() + b.intValue());
         System.out.println(g.equals(Integer.valueOf(a.intValue() + b.intValue())));

         */
    }
}
