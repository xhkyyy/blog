package singleton;


/**
 * 权衡性能来说最好的单例惯用写法。
 * 特点：
 * 1）instance 变了必须是 volatile，防止指令冲排序（volatile 两大作用，一是防止指令冲排序，二是保证多线程的可见性）
 * 2）在 getInstance() 方法中，将 instance 赋值给一个 obj 临时变量，将获得较好的性能
 * (来自于 《java 性能权威指南》, 但性能测试中并没有体现出太大的差异来)
 * 3）假如 Singleton3 是线程安全的，那么，将 synchronized 同步放在 'if (obj == null) {' 将不会引入同步瓶颈。
 * （在 Singleton3 假设是线程安全的情况下，如果 getInstance() 多线程频繁的执行，再引入 synchronized，将产生性能瓶颈）
 */
public class Singleton3 {

    /**
     * 使用 volatile 有 2 个目的
     * 1）确保 new Singleton3() 被完全初始化完成
     * 2）确保在多线程环境下，其它线程能够立即看到 instance 值的变化
     */
    private volatile static Singleton3 instance = null;
    
    private Singleton3() {
    }

    public static Singleton3 getInstance() {

        // 使用局部变量 obj 是为了提高性能：
        // 原因：访问 volatile 变量会导致刷新缓存行，导致 CPU 缓存失效（CPU 高速缓存和主存之间对同步）。
        // 通过减少对 volatile 变量的访问，避免不必要对同步、缓存行失效。
        Singleton3 obj = instance;

        if (obj == null) {
            synchronized (Singleton3.class) {
                obj = instance;
                if (obj == null) {
                    obj = new Singleton3();
                    instance = obj;
                }
            }
        }

        return instance;
    }
}
