package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author woshi
 * @date 2020/6/5
 * 创建一个Semaphore对象，设置只有一个线程可以运行，线程t1开始启动，调用acquire方法限制其他线程运行，for循环中添加4个对象后，调用release()表示其他线程可以运行
 * 这时在t1线程中启动t2线程，调用join()把CPU控制权交给t2线程，t2打印出结束提示。并继续输出后来的对象添加信息，。
 */
public class Semaphore_1 {
    //添加volatile，使得t2能够感知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    static Thread t1, t2 = null;

    public static void main(String[] args) {
        Semaphore_1 t = new Semaphore_1();
        Semaphore s = new Semaphore(1);

        t1 = new Thread(()->{
            try {
                s.acquire();
                for(int i = 0; i < 5; i++){
                    t.add(new Object());
                    System.out.println("add:"+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                s.acquire();
                for(int i = 5; i < 10; i++){
                    t.add(new Object());
                    System.out.println("add"+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t2 = new Thread(()->{
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t1.start();
    }
}