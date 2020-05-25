package collectionDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author taosh
 * @create 2020-05-21 10:03
 * LinkedBlockingQueue,用链表实现的BlockingQueue，是一个无界队列。
 * 它可以一直装到你内存满了为止，一直添加
 */
public class LinkedBlockingQueueDemo {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(()->{
            for( int i = 0; i < 100; i++ ){
                try {
                    // put往里面加内容，BlockingQueue在Queue的基础上又增加了两个方法，put、take
                    // 这两个方法是真真正正的实现了阻塞
                    // put往里装如果满了的话我这个线程会阻塞住，take往外取如果空了的话线程会阻塞住
                    // 所以这个BlockingQueue就实现了生产者消费者里面的那个容器
                    strs.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        // 每装一个的时候睡 1秒钟。然后，后面又启动了 5个线程不断的从里面take,
        // 空了我就等着，什么时候新加了我就马上给它取出来
        for( int i = 0; i < 5; i++ ){
            new Thread(()->{
                for (;;){
                    try {
                        //如果空了就会等待
                        System.out.println(Thread.currentThread().getName()+" take - "+ strs.take());
                        System.out.println("取出");
                    }catch ( Exception e ){
                        e.printStackTrace();
                    }
                }
            }, "c"+i).start();
        }
    }
}
