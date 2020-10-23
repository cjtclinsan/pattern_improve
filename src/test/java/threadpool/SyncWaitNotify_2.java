package threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class SyncWaitNotify_2 {
    private static volatile boolean t2Started = false;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {

        final Object o = new Object();
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        new Thread(()->{
//            latch.countDown();
            synchronized (o) {
                while ( !t2Started ){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : c1) {
                    System.out.println(c);

                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                o.notify();
            }
        }, "t1").start();

        new Thread(()->{
            synchronized (o) {
                for (char c : c2) {
                    System.out.println(c);
//                    latch.countDown();
                    t2Started = true;

                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
}