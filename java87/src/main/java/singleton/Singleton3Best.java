package singleton;


/**
 * 权衡性能来说最好的单例惯用写法。
 * 特点：
 * 1）instance 变了必须是 volatile，防止指令冲排序（volatile 两大作用，一是防止指令冲排序，二是保证多线程的可见性）
 * 2）在 getInstance() 方法中，将 instance 赋值给一个 obj 临时变量，将获得较好的性能？
 *    (来自于 《java 性能权威指南》, 但性能测试中并没有体现出太大的差异来)
 * 3）假如 Singleton3Best 是线程安全的，那么，将 synchronized 同步放在 'if (obj == null) {' 将不会引入同步瓶颈。
 * （在 Singleton3Best 假设是线程安全的情况下，如果 getInstance() 多线程频繁的执行，再引入 synchronized，将产生性能瓶颈）
 */
public class Singleton3Best {

    private volatile static Singleton3Best instance = null;

    public static Singleton3Best getInstance() {
        Singleton3Best obj = instance;

        if (obj == null) {
            synchronized (Singleton3Best.class) {
                obj = instance;
                if (obj == null) {
                    obj = new Singleton3Best();
                    instance = obj;
                }
            }
        }

        return instance;
    }
}
