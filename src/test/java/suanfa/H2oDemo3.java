package suanfa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author taosh
 * @create 2020-05-20 15:22
 */
public class H2oDemo3 {
    public H2oDemo3() {
    }

    Lock lock = new ReentrantLock();
    Condition con = lock.newCondition();
    volatile int index = 0;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while ( index == 2 ){
                con.await();
            }
            releaseHydrogen.run();
            index++;
            con.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while ( index < 2 ){
                con.await();
            }
            releaseOxygen.run();
            index = 0;
            con.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        H2oDemo1 h2o = new H2oDemo1();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    h2o.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    h2o.oxygen(()-> System.out.print("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
