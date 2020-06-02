package cas;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/6/1
 * ReentrantLock还可以指定为公平锁
 * 意思是：当我们new一个ReentrantLock时，我们可以传一个参数true，表示公平锁：线程上来会先检查队列里有没有原来等待的，如果有的话，
 * 就先进入队列等着别人先运行。意思是谁在前面就让谁执行，
 * 如果是不公平的，来一个线程就抢，而且有可能抢到，
 */
public class ReentrantLock_5 extends Thread{
    public static ReentrantLock lock = new ReentrantLock(false);

    public void run(){
        for(int i = 0; i < 10; i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock_5 r = new ReentrantLock_5();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
    }
}