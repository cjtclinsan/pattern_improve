package threadpool;

import org.springframework.beans.factory.ObjectProvider;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class SyncWaitNotify {
    public static void main(String[] args) {
        final Object o = new Object();

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        new Thread(()->{
            synchronized (o) {
                for (char c : c1) {
                    try {
                        o.notify();
                        o.wait();           //让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(c);
                }

                o.notify();      //必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(()->{
            synchronized (o) {
                for (char c : c2) {
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
        }, "t2").start();

    }
}