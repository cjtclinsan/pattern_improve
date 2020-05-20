package suanfa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author taosh
 * @create 2020-05-20 13:55
 */
public class ZeroEvenOddDemo2 {
    private int n;

    public ZeroEvenOddDemo2(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock();
    Condition z = lock.newCondition();
    Condition num = lock.newCondition();

    volatile boolean zTrun = true;
    volatile int zIndex = 0;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(;zIndex < n ;){
            lock.lock();
            try {
                while ( !zTrun ){
                    z.await();
                }
                printNumber.accept(0);
                zTrun = false;
                num.signalAll();
                zIndex++;
            }finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for( int i = 2; i <= n; i += 2 ){
            lock.lock();
            try {
                while ( zTrun || (zIndex & 1) == 1){
                    num.await();
                }
                printNumber.accept(i);
                zTrun = true;
                z.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for( int  i = 1; i <= n; i += 2 ){
            lock.lock();
            try {
                while (zTrun || (zIndex & 1) == 1){
                    num.await();
                }
                printNumber.accept(i);
                zTrun = true;
                z.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        new Thread(()->{
            try {
                zeroEvenOdd.zero((x)-> System.out.println(x + " " + Thread.currentThread().getName()));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }, "0").start();

        new Thread(()->{
            try {
                zeroEvenOdd.even((x)-> System.out.println(x + " " + Thread.currentThread().getName()));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }, "1").start();

        new Thread(()->{
            try {
                zeroEvenOdd.odd((x)-> System.out.println(x + " " + Thread.currentThread().getName()));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }, "2").start();
    }
}
