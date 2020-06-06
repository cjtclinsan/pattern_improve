package jucTool;

import java.util.concurrent.Exchanger;

/**
 * @author woshi
 * @date 2020/6/3
 * 交换器：用于两个线程之间交换数据
 * 第一个线程有一个成员变量s，然后exchanger.exchange(s)，第二个也是这样，t1线程名叫t1，t2线程叫t2，这两个线程中的变量会交换
 * 可以用于线程间的通信
 *
 * exchanger可以把它想象成一个容器，这个容器有两个值:两个线程。第一个线程执行到exchanger.exchange()的时候，阻塞。
 * 但是在执行exchange方法的时候就是往容器里面扔一个值（t1），同理t2执行到exchange方法时，也往容器里扔一个值（t2），
 * 接下来t1，t2互换，然后两个线程继续往下执行；
 *
 * exchange只能是两个线程之间
 */
public class ExchangerDemo {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";

            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"  "+s);
        }, "t1").start();

        new Thread(()->{
            String s = "T2";

            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"  "+s);
        }, "t2").start();
    }
}