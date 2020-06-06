package firstStageSummary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author woshi
 * @date 2020/6/5
 * 我们要阻塞或者唤醒一个具体的线程有很多限制：
 * 1、因为wait()方法需要释放锁，所以必须在synchronized中使用，否则会抛出异常 IllegalMonitorStateException
 * 2、notify()也必须要在synchronized中使用，并且应该指定对象
 * 3、synchronized(),wait(),notify()对象必须一致，一个synchronized()代码块中只能有一个线程调用wait(),notify()
 *
 * LockSupport()是一个比较底层的工具，用来创建锁和其他同步工具类的基本线程阻塞原语。
 * java锁和同步器框架核心AQS：AbstractQueuedSynchronizer，就是通过调用LockSupport.park()和LockSupport.unpark()方法，来实现线程的阻塞和唤醒的
 *
 * 下面的小程序可以看出：使用LockSupport.park()使当前线程阻塞，方法并没有加锁，默认是当前线程阻塞
 *
 * 调用LockSupport.unpark()方法，可以唤醒某个具体的线程
 * 下面程序中，主线程"t"中调用了start()后，紧接着调用LockSupport.unpark()方法，，就是说在t线程还没有执行park阻塞方法的时候，
 * 就已经调用了unpark()方法来唤醒t，之后t再调用park()方法，但线程t并没有被阻塞，由此可以看出unpark()可以先于park()方法执行
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i = 0; i < 10; i++) {
                System.out.println(i);

                if (i == 5) {
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
        //LockSupport.unpark(t);
    }
}