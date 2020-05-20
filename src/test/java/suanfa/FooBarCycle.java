package suanfa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author taosh
 * @create 2020-05-20 11:25
 */
public class FooBarCycle {
    private int n;
    private volatile boolean flag = false;

    private CountDownLatch latch;
    //使用CyclicBarrier保证任务按组执行
    private CyclicBarrier barrier;

    public FooBarCycle(int n) {
        this.n = n;
        latch = new CountDownLatch(1);
        barrier = new CyclicBarrier(2);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        try {
            for( int i = 0; i < n; i++ ){
                printFoo.run();
                // printFoo方法完成调用countDown
                latch.countDown();
                // 等待printBar方法执行完成
                barrier.await();
            }
        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        try {
            for (int i = 0; i < n; i++) {
                // 等待printFoo方法先执行
                latch.await();
                printBar.run();
                // 保证下一次依旧等待printFoo方法先执行
                latch = new CountDownLatch(1);
                // 等待printFoo方法执行完成
                barrier.await();
            }
        }catch ( Exception e ){

        }
    }

    public static void main(String[] args) {
        FooBarCycle cycle = new FooBarCycle(20);

        Thread t1 = new Thread(() -> {
            try {
                cycle.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                cycle.bar(()-> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }
}
