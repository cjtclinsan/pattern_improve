package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 方法三：
 * 启用两个线程，线程1给object加锁，用for循环来增加对象，当数量为5时，通过notify来唤醒线程2；
 * 线程2，给object对象加锁，判断数量不为5的时候，调用wait方法阻塞线程，先启动（监听），通过线程1的notify来唤醒
 * 结果失败，原因:
 * notify()方法不释放锁，当t1线程调用notify方法后，并没有释放当前锁，t1还是会直执行下去，知道t1执行完毕，t2线程才会被唤醒，这个时候size数量为10个。
 */
public class NotifyHoldingLock_1 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        NotifyHoldingLock_1 t = new NotifyHoldingLock_1();
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
                }
                System.out.println("t2 结束");
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