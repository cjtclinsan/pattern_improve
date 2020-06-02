package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/6/1
 * lock.lockInterruptibly()，对interrupt()方法做出响应
 * 线程1上来后加锁，然后开始sleep，这个时候如果线程2想拿到锁基本不太可能。拿不到就在那等待，这种情况使用lock.lock()是打断不了的
 * 这个时候就可以用另一种方式lock.lockInterruptibly这个类可以被打断。当你要停止线程2就可以使用interrupt()。
 * 这也是ReentrantLock比synchronized好用的地方
 */
public class ReentrantLock_4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly();   //可以对interrupt()方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t2 end");
            } catch ( InterruptedException e ){
                System.out.println("interrupted");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        t2.interrupt();
    }
}