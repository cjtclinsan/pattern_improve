package secondStageSummary;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woshi
 * @date 2020/6/6
 * 一个固定容量的同步容器，拥有get和put，以及getCount方法，能够支持2个生产者线程和10个消费者的阻塞调用
 *
 * 生产者只叫醒消费者线程，消费者只叫醒生产者线程
 *
 * ReentrantLock可以有两种Condition条件，在put()方法里是我们的生产线程，生产者线程lock()，最后unlock()，一旦count到达峰值的时候producer.await(),
 * 然后consumer.signalAll()，在producer的情况下只叫醒consumer；
 * 在get()方法里是我们的消费者线程，一旦count==0，调用consumer.await(),然后producer.signalAll(),只叫醒生产者线程。
 * 这就是ReentrantLock的含义，他能精确指定哪些（不是哪个）线程被叫醒，
 *
 * Lock和Condition的本质:
 * 在synchronized里调用wait()和notify()的时候，只有一个等待队列，如果是lock.newCondition()，就会变成多个等待队列。
 * Condition的本质就是等待队列的个数，new两个Condition，一个叫producer，一个等待队列；一个叫consumer，第二个等待队列。
 * 当使用producer.await()的时候，就是进入producer的等待队列，，使用producer.signalAll()就是唤醒producer这个等待队列里的线程，consumer也是如此。
 *
 */
public class MyContainer_2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    //最多十个元素
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    //生产者
    public void put(T t){
        try {
            lock.lock();
            while (count == MAX){
                producer.await();
            }

            lists.add(t);
            ++count;
            //通知消费者进行消费
            consumer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //消费者
    public T get(){
        T t = null;

        try {
            lock.lock();
            while (count == 0){
                consumer.await();
            }

            t = lists.removeFirst();
            count--;
            //通知生产者进行生产
            producer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer_2<String> c = new MyContainer_2<>();

        //启动消费者线程
        for (int i = 0; i < 10; i++){
            new Thread(()->{
                for (int j = 0; j < 5; j++) System.out.println(c.get());
            }, "c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者生产
        for( int i = 0; i <2; i++ ){
            new Thread(()->{
                for(int j = 0; j < 25; j++) c.put(Thread.currentThread().getName()+":"+j);
            }, "p"+i).start();
        }
    }
}