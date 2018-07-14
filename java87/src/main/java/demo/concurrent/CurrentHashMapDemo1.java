package demo.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WY on 2015/10/21.
 */
public class CurrentHashMapDemo1 {

    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    //方法1
    public static void putBeforeJava8_1(String key){

        Long oldVal;
        Long newVal;

        do {
            oldVal = map.get(key);
            newVal = (oldVal != null ? (oldVal + 1) : 1);
        }while (!map.replace(key, oldVal, newVal));

    }

    //方法2:ConcurrentHashMap<String, AtomicLong>


    /*方法3.
        LongAdder出色的性能!
        map.putIfAbsent(key, new LongAdder()).increment();
     */


    public static void main(String[] args) {


        ConcurrentHashMap<String, Long> m = new ConcurrentHashMap<>();
        m.put("ab", 1L);
        m.put("cd", 3L);
        m.put("ef", 10L);

        System.out.println("reduce val=" + m.reduceValues(0, Long::sum));
        System.out.println("reduce val=" + m.reduceValues(0, Long::max));


    }

}
