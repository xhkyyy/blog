package error;

import org.junit.Test;

/**
 * Created by Len on 03/02/2017.
 */
public class TestException {

	@Test
	public void test1(){
		int i = 1 / 0;
		System.out.println("i=" + i);
	}


}
