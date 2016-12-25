package copyjava;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Len on 17/12/2016.
 */
public class ConMapTest {

	public static void main(String[] args) {

		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);

		map.put(1, "hello");
		map.put(2, "world");

		String val = map.get(1);

		System.out.println("value=" + val);

	}

}
