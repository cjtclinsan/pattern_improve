package atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author woshi
 * @date 2020/6/1
 * 由结果可知:Atomic比Sync快，因为synchronized是要加锁的，有可能需要去操作系统去申请重量级锁。
 * LongAddr比Atomic快：LongAddr内部做了一个分段锁，在它内部的时候，会把一个值放到一个数组里面，比如说数组长度为4，
 * 一共1000个线程，250个线程放在第一个数组里面，以此类推，各一个都往上递增，结果再加在一起。
 */
public class AtomicVsSyncVsLongAddr {
    static AtomicLong count1 = new AtomicLong(0);
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for(int i =0; i < threads.length; i++){
            threads[i] = new Thread(()->{
               for(int k = 0; k < 100000; k++){
                   count1.incrementAndGet();
               }
            });
        }

        long start = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();

        System.out.println("atomic:"+ count1 +",time:"+(end-start)+"ms");
        System.out.println("=============================");

        Object lock = new Object();
        for(int i =0; i < threads.length; i++){

            threads[i] = new Thread(()->{
                for(int k = 0; k < 100000; k++){
                    synchronized (lock){
                        count2++;
                    }
                }
            });
        }

        long start1 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end1 = System.currentTimeMillis();

        System.out.println("synchronized:"+ count2+ ",time"+(end1-start1)+"ms");
        System.out.println("=============================");

        for(int i =0; i < threads.length; i++){

            threads[i] = new Thread(()->{
                for(int k = 0; k < 100000; k++){
                    count3.increment();
                }
            });
        }

        long start2 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end2 = System.currentTimeMillis();

        System.out.println("LongAddr:"+ count3.longValue()+ ",time" +(end2-start2)+"ms");
    }
}