package volatileAndCAS;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/31
 * 锁优化:
 * 1、把粒度变细----在锁征用不是非常剧烈的情况下，粒度最好小一点
 * 2、锁粒度变粗----在征用特别频繁的情况下，如果锁粒度太细，会征用特别多的锁，容易发生死锁等情况。还不如直接征用一把大锁，反而没这么频繁。
 */
public class FineCoarseLock {
    int count = 0;
    synchronized void m1(){
        //do sth with no sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑需要加锁的地方,这事就不应该给整个方法加锁
        count++;

        //do sth with no sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2(){
        //do sth with no sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑需要加锁的地方,这事就不应该给整个方法加锁
        //采用细粒度的锁，使得线程征用锁的时间变短，提升效率
        synchronized (this){
            count++;
        }

        //do sth with no sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}