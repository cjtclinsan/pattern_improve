package threadpool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class LockCondition_2 {
    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();

                for (char c : c1) {
                    System.out.println(c);
                    conditionT2.signal();
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(()->{
            try {
                lock.lock();

                for (char c : c2) {
                    System.out.println(c);
                    conditionT1.signal();
                    conditionT2.await();
                }

                conditionT1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}