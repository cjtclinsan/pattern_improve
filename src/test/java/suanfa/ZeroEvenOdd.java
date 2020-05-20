package suanfa;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author taosh
 * @create 2020-05-20 11:58
 */
public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    Semaphore z = new Semaphore(1);
    Semaphore e = new Semaphore(0);
    Semaphore o = new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for ( int i = 0; i < n; i++ ){
            z.acquire();
            printNumber.accept(0);
            if( (i & 1) == 0 ){
                o.release();
            }else {
                e.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for( int i = 2; i <= n; i += 2 ){
            e.acquire();
            printNumber.accept(i);
            z.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for( int  i = 1; i <= n; i += 2 ){
            o.acquire();
            printNumber.accept(i);
            z.release();
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
