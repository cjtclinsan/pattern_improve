package volatileAndCAS;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/30
 * volatile关键字，是一个变量在多个线程之间可见
 * A B 线程都是用一个变量，Java默认线程A中保留一份copy，如果线程B修改了变量，线程A未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 下面的线程中，running存在于堆内存的t对象中
 * 当线程t开始运行的时候，会把running值从内存中读取到线程t的工作区，在运行的过程中直接使用这个copy，不会每次去取堆内存
 * 先其他线程修改running的值后，t线程会感知不到，不会停止运行
 *
 * 而是用volatile，会强制所有线程去堆内存中去读取running的值
 * 但volatile不能保证多个线程共同修改running变量带来的不一致问题，就是说volatile不能替代synchronized
 *  */
public class HelloVolatile_1 {
    static volatile boolean running = true;

    void m(){
        System.out.println("m start");
        while (running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        HelloVolatile_1 t = new HelloVolatile_1();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}