package design_pattern.singleton;

/**
 * Created by Len on 27/01/2017.
 */
public class Singleton1 {
	private static volatile Singleton1 instance = null;

	private Singleton1(){

	}

	public static Singleton1 getInstance(){
		if(instance == null){
			synchronized (Singleton1.class){
				if(instance == null){
					//增加volatile修饰，防止实例还没有初始化完毕（指令排序）
					instance = new Singleton1();
				}
			}
		}
		return instance;
	}
}
