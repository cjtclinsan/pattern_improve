package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/29
 * 程序在执行过程中，如果出现异常，默认情况会释放锁
 * 所以在并发处理的过程中，有异常需要多加小心，不然会发生不一致的情况
 * 比如在一个web app处理过程中，多个servlet线程共同访问一个资源，如果异常处理不合适
 * 在第一个线程抛出异常时，其他线程会进入到同步代码区，有可能会访问到异常产生时的数据
 */
public class SynchronizedExceptionDemo {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (true){
            count++;

            System.out.println(Thread.currentThread().getName()+" count = "+count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //被其他准备拿到这个锁的线程冲进来
            if( count == 5 ){
                //模拟异常
                int i = 1/0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExceptionDemo demo = new SynchronizedExceptionDemo();
        new Thread(demo::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(demo::m, "t2").start();
    }
}