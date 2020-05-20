package suanfa;

import java.util.function.IntConsumer;

/**
 * @author taosh
 * @create 2020-05-20 14:24
 */
public class ZeroEvenOddDemo3 {
    private int n;

    public ZeroEvenOddDemo3(int n) {
        this.n = n;
    }

    volatile int stage = 0;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n; i++){
            while ( stage > 0 );
            printNumber.accept(0);

            if( (i & 1) == 0 ){
                stage = 1;
            }else {
                stage = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for( int i = 2; i <= n; i += 2 ){
           while ( stage != 2 );
           printNumber.accept(i);
           stage = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for( int i = 1; i <= n; i += 2 ){
            while ( stage != 1 );
            printNumber.accept(i);
            stage = 0;
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
