package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/6/1
 * ReentrantLock强大的地方在于可以使用tryLock尝试进行锁定，不管是否锁定，方法都将继续进行。
 * synchronized如果搞不定的话就肯定阻塞了，但是用ReentrantLock你就可以自己决定到底要不要wait
 */
public class ReentrantLock_3 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for(int i = 0; i < 3; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来决定锁定
     * 也可以指定trylock的时间
     */
    void m2(){
        boolean locked = false;

        try {
            locked = lock.tryLock(5 ,TimeUnit.SECONDS);
            System.out.println("m2 ..."+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock_3 t = new ReentrantLock_3();
        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}