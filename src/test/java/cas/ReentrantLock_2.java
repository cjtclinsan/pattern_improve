package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/6/1
 * ReentrantLock是可以替换synchronized的，在原来synchronized的地方换成Lock.lock()，需要注意的是lock.unlock()
 * 由于synchronized是自动解锁的，执行完就结束了。
 * lock必须手动解锁，一定要写在try...finally...保证一定要解锁。
 */
public class ReentrantLock_2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();   //synchronized(this)
            for (int i = 0; i < 10; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lock();
            System.out.println("m2 ...");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock_2 t1 = new ReentrantLock_2();
        new Thread(t1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t1::m2).start();
    }
}