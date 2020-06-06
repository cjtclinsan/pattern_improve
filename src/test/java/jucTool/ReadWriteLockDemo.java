package jucTool;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author woshi
 * @date 2020/6/2
 * 读写锁：共享锁和排他锁
 * 读锁就是共享锁，写锁就是排他锁
 */
public class ReadWriteLockDemo {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //拿到读锁，读线程可以一起读
//        Runnable readR = ()->read(readLock);
//        Runnable writeR = ()->write(writeLock, new Random().nextInt());

        Runnable readR = ()->read(lock);
        Runnable writeR = ()->write(lock, new Random().nextInt());

        for(int i = 0; i < 18; i++){
            new Thread(readR).start();
        }

        for(int i = 0; i < 2; i++){
            new Thread(writeR).start();
        }
    }
}