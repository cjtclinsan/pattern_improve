package threadpool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class LockCondition_1 {
    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                for (char c : c1) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e){
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
                    condition.signal();
                    condition.await();
                }

                condition.signal();
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}