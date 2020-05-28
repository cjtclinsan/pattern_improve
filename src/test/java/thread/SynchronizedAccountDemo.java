package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/28
 *
 * 对业务写加锁，读不加锁
 * 读的时候可以读取到中间结果产生的内存，脏读
 * 是由于synchronized和非synchronized方法同时运行导致的
 */
public class SynchronizedAccountDemo {
    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public double getBalance(String name){
        return this.balance;
    }

    /**
     * 两次读取到的balance不一致，解决方案+synchronized，效率会降低
     * @param args
     */
    public static void main(String[] args) {
        SynchronizedAccountDemo demo1 = new SynchronizedAccountDemo();
        new Thread(()->demo1.set("zhangsan", 100)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo1.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo1.getBalance("zhangsan"));
    }
}