package jucTool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author woshi
 * @date 2020/6/2
 * 循环栅栏:有一个栅栏，什么时候人满了就把栅栏放开，然全都过去，然后再把栅栏立起来，等待下一次满
 *
 */
public class CyclicBarriierDemo {
    public static void main(String[] args) {
        //满20个之后，做sth
        CyclicBarrier barrier = new CyclicBarrier(20, ()-> System.out.println("20人满了，开车"));
        //可以装满5次
        for (int i = 0; i < 100; i++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}