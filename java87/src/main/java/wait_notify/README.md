# wait() & notify()

+ wait/notify 方法在调用前，必须首先确保已经获取到了对象到锁（本例是将 wait/notify 到执行包裹在 synchronized 代码块中）

+ [wait_notify.func.TestWait](java87/src/main/java/wait_notify/func/TestWait.java)说明： 如果线程 A 还没有执行 wait 语句，但此时另一个线程 B 执行了 notify，那么，等线程 A 未来执行到 wait 时仍然会被阻塞住。也即是 notify 执行前必须确保线程已经被 wait 了。


