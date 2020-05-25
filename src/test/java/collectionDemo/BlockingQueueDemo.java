package collectionDemo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author taosh
 * @create 2020-05-21 9:56
 * BlockingQueue的概念重点是在Blocking上，Blocking阻塞， Queue队列 => 阻塞队列
 * 提供了一系列的方法，我们可以在这些方法的基础之上做到让线程实现自动的阻塞。
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedDeque<>();

        for( int i = 0; i< 10; i++ ){
            // offer对应的是原来的add,offer是往里头添加,加进去没加进去它会给你个布尔类型的返回值
            // add如果加不进去了是会抛异常的
            strs.offer("a"+i);
        }
        System.out.println(strs);
        System.out.println(strs.size());

        // poll是取并且remove掉
        System.out.println(strs.poll());
        System.out.println(strs.size());

        // peek的概念是去取并不是让你remove掉
        System.out.println(strs.peek());
        System.out.println(strs.size());

        //这几个对于BlockingQueue来说是线程安全的一个操作
    }
}
