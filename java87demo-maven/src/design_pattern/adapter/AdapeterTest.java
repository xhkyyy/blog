package design_pattern.adapter;

import org.junit.Test;

/**
 * Created by Len on 28/01/2017.
 */
public class AdapeterTest {

	@Test
	public void testClassAdapter(){
		System.out.println("\n\n");
		Target target = new ClassAdapter();
		target.request();
	}

	@Test
	public void testObjectAdapter(){
		System.out.println("\n\n");
		Target target = new ObjectAdapter();
		target.request();
	}


}
