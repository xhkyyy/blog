package error;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 25/12/2016.
 */
public class HeapError {

	public static void main(String[] args) {

		List<Object> list = new ArrayList<>();
		while (true) {
			list.add(new Object());
		}

	}

}
