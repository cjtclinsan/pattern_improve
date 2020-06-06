package firstStageSummary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author woshi
 * @date 2020/6/5
 * 这个程序的线程t被阻塞了，原因：
 * LockSupport的unpark()就像是线程获得了一个“令牌”，而park()方法就像是识别"令牌"，一个令牌只能被识别一次就被作废，所以第二次park()导致线程被阻塞
 *
 * 结论：
 * LockSupport不需要synchronized加锁就可以实现线程的阻塞和唤醒
 * LockSupport.unpark()可以先于LockSupport.park()执行，并且线程不会阻塞
 * 如果一个线程处于等待状态，连续调用两次park(),就会使线程永远无法唤醒
 *
 * park()和unpark()方法的实现是由Unsafe()类提供的，原理也比较好理解：
 * 通过一个变量作为一个标识，变量值在0，1之间来回切换，当这个变量大于0的时候线程就获得"令牌"，
 * park()和unpark()就是在改变变量的值，来达到现成的阻塞和唤醒的
 */
public class LockSupportDemo_2 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i = 0; i < 10; i++) {
                System.out.println(i);

                if (i == 5) {
                    //使用LockSupport的park来阻塞当前线程
                    LockSupport.park();
                }

                if(i == 8){
                    //使用LockSupport的park来阻塞当前线程
                    LockSupport.park();
                }

                try {
                    //使当前线程t休眠1s
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        //唤醒线程
        LockSupport.unpark(t);
    }
}