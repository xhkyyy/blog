package singleton;

/**
 * 性能最好的实现单例的方法
 */
public class Singleton2 {

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        return InstanceHolder.instance;
    }


}
