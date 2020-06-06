package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 方法四：
 * 启用两个线程，线程1给object加锁，用for循环来增加对象，当数量为5时，通过notify来唤醒线程2；
 * 线程2，给object对象加锁，判断数量不为5的时候，调用wait方法阻塞线程，先启动（监听），通过线程1的notify来唤醒
 * 成功，原因：
 * 程序三失败的原因是notify()方法是不会释放锁的，所以在t1中，notify后紧接着又调用wait()方法来阻塞t1，释放t1的锁，来实现t2的实时监控
 * t2执行结束后，打印相应提示，再调用notify()唤醒t1线程，让t1线程继续执行完
 */
public class NotifyHoldingLock_2 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        NotifyHoldingLock_2 t = new NotifyHoldingLock_2();
        final Object lock = new Object();
        //需要之一先启动t2，再启动t1
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 启动");
                if( t.size() != 5 ){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 结束");
                    //通知t1继续执行
                    lock.notify();
                }
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (lock){
                for(int i = 0; i < 10; i++){
                    t.add(new Object());
                    System.out.println("add:"+i);

                    if( t.size() == 5 ){
                        lock.notify();

                        //释放锁，让t2得以执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}