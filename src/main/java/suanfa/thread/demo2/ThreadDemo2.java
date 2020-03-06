package suanfa.thread.demo2;

import java.util.LinkedList;

/**
 * 有3个线程和1个公共的字符数组。线程1的功能就是向数组输出A，
 * 线程2的功能就是向字符输出l，线程3的功能就是向数组输出i。
 * 要求按顺序向数组赋值AliAliAli，Ali的个数由线程函数1的参数指定。
 * @author taosh
 * @create 2020-03-04 11:32
 */
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<String> list = new LinkedList<>();
        for( int i = 0 ; i < 3; i++ ){
            Thread thread1 = new ThreadFile("a",list);
            Thread thread2 = new ThreadFile("l",list);
            Thread thread3 = new ThreadFile("i",list);

            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
        }

        System.out.println(list);
    }
}
