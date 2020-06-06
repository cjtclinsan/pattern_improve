package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 采用await()方法替代了t2线程和t1线程中的wait()方法，
 * t2线程开始启动，判断size!=5，调用await()方法阻塞t2线程，t1线程开始添加对象，当对象添加到5个时，打开门闩让t2继续执行
 *
 * 解决：在t1线程打开t2门闩的时候，自己再建一个门闩，再t2执行完后，给t1门闩解了
 *
 */
public class CountDownLatch_2 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        CountDownLatch_2 t = new CountDownLatch_2();
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(()->{
            System.out.println("t2 start...");
            if( t.size() != 5 ){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch2.countDown();
            System.out.println(t.size());
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
                    try {
                        //暂停t1线程
                        latch.countDown();
                        //给t1上门闩，让t2执行
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        }, "t1").start();
    }
}