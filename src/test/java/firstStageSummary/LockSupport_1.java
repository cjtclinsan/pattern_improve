package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author woshi
 * @date 2020/6/5
 * 使用LockSupport实现原理与synchronized和latch大同小异
 * 在t1线程调用unpark(t2)方法唤醒t2线程之后，调用park()方法阻塞，在t2线程执行完后，调用unpark(t1)再唤醒t1继续执行
 */
public class LockSupport_1 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    static Thread t1, t2 = null;

    public static void main(String[] args) {
        LockSupport_1 t = new LockSupport_1();
        CountDownLatch latch = new CountDownLatch(1);

        Thread t2 = new Thread(()->{
            System.out.println("t2 start...");
            if( t.size() != 5 ){
                LockSupport.park();
            }
            System.out.println("t2 end...");
            LockSupport.unpark(t1);
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1 = new Thread(()->{
            System.out.println("t1 start...");
            for(int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add:" + i);

                if (t.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");
        t1.start();
    }
}