package threadpool;

import java.util.concurrent.locks.LockSupport;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class LockSupport_1 {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        t1 = new Thread(()->{
            for (char c : c1) {
                System.out.println(c);
                LockSupport.unpark(t2);   //叫醒t2
                LockSupport.park();       //ti阻塞
            }
        }, "t1");

        t2 = new Thread(()->{
            for (char c : c2) {
                LockSupport.park();         //t2阻塞
                System.out.println(c);
                LockSupport.unpark(t1);     //叫醒t1
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}