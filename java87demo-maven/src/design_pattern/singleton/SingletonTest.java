package design_pattern.singleton;

import org.junit.Test;

/**
 * Created by Len on 27/01/2017.
 */
public class SingletonTest {

	@Test
	public void test1(){
		System.out.println("Singleton1=" + Singleton1.getInstance());
	}

	@Test
	public void test2(){
		System.out.println("Singleton2=" + Singleton2.getInstance());
	}

}