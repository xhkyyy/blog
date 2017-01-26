package singleton;

/**
 * Created by Len on 25/12/2016.
 */
public class Singleton2 {

	private static class InstanceHolder{
		public static Instance instance = new Instance();
	}

	public static Instance getInstance(){
		return InstanceHolder.instance;
	}


}
