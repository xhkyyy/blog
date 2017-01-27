package design_pattern.singleton;

/**
 * Created by Len on 27/01/2017.
 */
public class Singleton2 {

	private static class InitClassSingleton{
		static Singleton2 instance = new Singleton2();
	}

	public static Singleton2 getInstance(){

		//将导致InitClassSingleton的初始化，执行类的初始化期间，jvm会加一把锁
		return InitClassSingleton.instance;
	}

}
