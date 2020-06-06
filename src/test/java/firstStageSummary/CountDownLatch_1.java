package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 采用await()方法替代了t2线程和t1线程中的wait()方法，
 * t2线程开始启动，判断size!=5，调用await()方法阻塞t2线程，t1线程开始添加对象，当对象添加到5个时，打开门闩让t2继续执行\
 *
 * 但是，当注释掉休眠的那段代码，会出现问题：
 * 当size=5时，countdown，此时门门闩被打开，但是紧接着t1又继续执行，t2没有时间去实时监控（执行），会出现数量不对的问题
 */
public class CountDownLatch_1 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        CountDownLatch_1 t = new CountDownLatch_1();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{
            System.out.println("t2 start...");
            if( t.size() != 5 ){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t2 end...");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 start...");

            for(int i = 0; i < 10; i++){
                t.add(new Object());
                System.out.println("add:"+i);

                if(t.size() == 5){
                    //暂停t1线程
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}