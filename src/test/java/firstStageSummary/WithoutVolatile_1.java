package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 实现一个容器，提供两个方法：add  size，写两个线程：
 * 1、添加10个元素到容器
 * 2、实时监控元素个数，当个数到达5的时候，给出提示并结束
 *
 * 程序一：t2中的c.size()永远没有检测到，原因是：线程与线程之间是不可见的
 */
public class WithoutVolatile_1 {
    List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        WithoutVolatile_1 t = new WithoutVolatile_1();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                t.add(new Object());
                System.out.println("add:"+i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(()->{
            while (true){
                if( t.size() == 5 ){
                    System.out.println("t2结束");
                    break;
                }
            }
        }, "t2").start();
    }
}