package cas;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class ReentrantLock_6 {
    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        i++;

        lock.unlock();
    }
}