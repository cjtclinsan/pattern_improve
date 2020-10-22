package list;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/22
 */
public class ArrayBlockingQueue_1 {
    static BlockingQueue<String> strs = new java.util.concurrent.ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for( int i = 0; i< 9; i++ ){
            strs.put("a"+i);
        }

        //strs.put("aaa");   //满了阻塞
        //strs.add("aaa");     //满了抛异常
        boolean flag = strs.offer("aaa");      //通过返回值来判断是否添加成功
        System.out.println(flag);

        strs.offer("aaa", 1, TimeUnit.SECONDS);


        System.out.println(strs);
    }
}