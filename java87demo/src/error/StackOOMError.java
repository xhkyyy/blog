package error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 25/12/2016.
 */
public class StackOOMError {

	public static void main(String[] args) {

		int count = 0;
		while (true){
			Thread t = new Thread(() -> {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			t.start();
			System.out.println("thread " + (++count));
		}



	}

}
