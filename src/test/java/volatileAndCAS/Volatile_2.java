package volatileAndCAS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author woshi
 * @date 2020/5/31
 * count值改变后只是被别的线程看见，但光看见没什么用，count++本身不是原子性的
 * volatile保证线程的可见性，并不能代替synchronized，保证不了原子性
 *
 * 保证原子性只是保证这些操作要么全部完成才能访问，和保证重排序是两码事
 */
public class Volatile_2 {
    volatile int count = 0;

    /*synchronized*/ void m(){
        count++;
    }

    public static void main(String[] args) {
        Volatile_2 t = new Volatile_2();


        for( int i = 0; i < 10000; i++ ){
            new Thread(()-> t.m()).start();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.count);
    }
}