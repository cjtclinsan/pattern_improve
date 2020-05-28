package thread;

/**
 * @author woshi
 * @date 2020/5/28
 * 同步方法和非同步方法是否可以同时调用？
 */
public class SynchronizedDemo2 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start ...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m1 end ...");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 start ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m2 end ...");
    }

    public static void main(String[] args) {
        SynchronizedDemo2 demo2 = new SynchronizedDemo2();

        new Thread(demo2::m1, "t1").start();
        new Thread(demo2::m2, "t2").start();

        /**
         * JDK 1.8前
         */
        new Thread(()->{
            demo2.m1();
        }).start();
    }
}