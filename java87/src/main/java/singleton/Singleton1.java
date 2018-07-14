package singleton;

/**
 * Created by Len on 25/12/2016.
 */
public class Singleton1 {

	private volatile static Instance instance = null;

	public static Instance getInstance() {
		if (instance == null) {
			synchronized (Singleton1.class) {
				if (instance == null) {
					//这并非一个院子操作，加入volatile防止指令重排序
					instance = new Instance();
				}
			}
		}
		return instance;
	}


}
