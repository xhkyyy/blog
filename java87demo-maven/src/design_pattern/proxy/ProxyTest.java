package design_pattern.proxy;

import org.junit.Test;

/**
 * Created by Len on 28/01/2017.
 */
public class ProxyTest {

	@Test
	public void test(){
		Image image = new ProxyImage();
		image.display();
	}

}
