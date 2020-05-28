package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/28
 * 模拟一个父子类的锁，父类Synchronized，子类调用super.m()的时候必须可重入
 * 所谓的重入就是拿到这把锁后不停得枷锁，但锁定的还是同一个对象
 */
public class SynchronizedReentrantLockDemo2 {
    synchronized void m(){
        System.out.println("m start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }

    static class TT extends SynchronizedReentrantLockDemo2{
        @Override
        synchronized void m() {
            System.out.println("child m start");
            super.m();
            System.out.println("child m end");
        }
    }
}