package collectionDemo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author taosh
 * @create 2020-05-21 10:30
 * 一个在并发的时候经常使用的一个类， 这个类叫CopyOnWrite，CopyOnWrite的意思叫写时复制。
 * CopyOnWriteList.CopyOnWriteSet
 */
public class CopyOnWriteDemo {
    public static void main(String[] args) {
        /** ArrayList会出现并发问题,因为多线程访问没有锁 */
        //List<String> lists = new ArrayList<>();
        /** Vector写的时候加锁，读的时候也加锁 */
        //List<String> lists = new Vector<>();
        /**
        * 当Write的时候我们要进行复制,当我们需要往里面加元素的时候先把里面的元素得复制出来，
        * 然后把你新添加这个元素扔到最后这个位置上,同时把指向老的容器的引用指向新的:写时复制
        * 读的时候不加锁，适合写少读多的情况
        */
//        List<String> lists = new CopyOnWriteArrayList<>();

        List<String> strs = new ArrayList<>();
        List<String> lists = Collections.synchronizedList(strs);

        Random r = new Random();

        Thread[] ths = new Thread[100];

        for( int i = 0; i < ths.length; i++ ){
            Runnable task = () -> {
              for( int k = 0; k < 1000; k++ ){
                  lists.add("a"+ r.nextInt(10000));
              }
            };

            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);

        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths){
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();

        System.out.println(s2 -s1+"ms");
    }
}
