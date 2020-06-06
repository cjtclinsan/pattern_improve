package secondStageSummary;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/6
 * 一个固定容量的同步容器，拥有get和put，以及getCount方法，能够支持2个生产者线程和10个消费者的阻塞调用
 *
 * 解：
 * 创建LinkedList用来保存"馒头"，MAX来限制馒头总数，定义count变量来判断生产了几个"馒头"和消费了几个"馒头"，
 * put()方法:判断linkedList中"馒头"是否MAX，如果是，启动消费者线程，反之开始生产,++count
 * get()方法:判断是否还有"馒头"，也就是count是否为0，如果是0，这通知生产者生产，反之不为0，"馒头"数继续减少 count--
 * 需要加synchronized，获取count的值会不准确
 */
public class MyContainer_1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    //最多十个元素
    final private int MAX = 10;

    private int count = 0;

    //生产者
    public synchronized void put(T t){
        //为什么不用if
        //if判断完==MAX以后，调用wait()方法以后，不会再去判断一次，方法继续往下执行
        //假如当你在wait()的时候，另一个方法又添加了一个"馒头",没有判断，就会再加一次，导致数据出错
        while (count == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;

        //通知消费者进行消费
        //notifyAll会叫醒等待队列的所有方法，所以有可能叫醒的还是生产者线程，又会重复上面的步骤
        this.notifyAll();
    }

    //消费者
    public synchronized T get(){
        T t = null;
        while (count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = lists.removeFirst();
        count--;
        //通知生产者进行生产
        this.notifyAll();

        return t;
    }

    public static void main(String[] args) {
        MyContainer_1<String> c = new MyContainer_1<>();

        //启动消费者线程
        for (int i = 0; i < 10; i++){
            new Thread(()->{
                for (int j = 0; j < 5; j++) System.out.println(c.get()+"  "+c.count);
            }, "c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者生产
        for( int i = 0; i <2; i++ ){
            new Thread(()->{
                for(int j = 0; j < 25; j++) c.put(Thread.currentThread().getName()+":"+j);
            }, "p"+i).start();
        }
    }
}