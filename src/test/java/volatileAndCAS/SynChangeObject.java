package volatileAndCAS;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/31
 * 锁定某对象o，如果o对象的属性发生变化，不影响锁的使用
 * 但是如果o变成了另一个对象，则锁定的对象发生变化
 * 应避免将锁定的对象引用变成其他对象
 */
public class SynChangeObject {
    //以对象作为锁的时候，加final
    final Object o = new Object();

    void m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        SynChangeObject t1 = new SynChangeObject();

        new Thread(t1::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(t1::m, "t2");

        //t1.o = new Object();  //锁对象发生变化，t2得以执行； 若注释，则t2永远无法执行

        t2.start();
    }
}