package collectionDemo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author taosh
 * @create 2020-05-21 9:44
 */
public class ArrayBlockingQueueDemo {
    public static BlockingQueue<String> strs = new ArrayBlockingQueue<String>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for ( int i = 0; i< 10; i++ ){
            strs.put("a"+i);
        }

        // 满了就会等待，程序阻塞
        //strs.put("aaa");

        // Exception:java.lang.IllegalStateException: Queue full
        //strs.add("aaa");

        // offer用返回值来判断到底加没加成功，
        // offer还有另外一个写法你可以指定一个时间尝试着往里面加1秒钟，1秒钟之后如果加不进去它就返回了。
        System.out.println(strs.offer("aaa"));
        System.out.println(strs.offer("aaa", 1, TimeUnit.SECONDS));

        System.out.println(strs);

        //Queue和List的主要区别:
        //queue添加了offer，peek，poll，take，put等这些对线程有好的阻塞或者等待方法。
    }
}
