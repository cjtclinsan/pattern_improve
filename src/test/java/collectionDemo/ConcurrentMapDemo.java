package collectionDemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author taosh
 * @create 2020-05-21 10:49
 * ConcurrentHashMap用hash表实现的这样一个高并发容器。
 * ConcurrentHashMap里面用的是cas操作，这个cas操作它用在tree的时候，
 * 用在树这个节点上的时候实现起来太复杂了，所以就没有这个ConcurrentTreeMap，
 * 但是有时间也需要这样一个排好序的Map, 那就有了ConcurrentSkipListMap跳表结构
 *
 * ConcurrentSkipListMap通过跳表来实现的高并发容器并且这个Map是有排序的。
 * 跳表:底层本身存储的元素一个链表，它是排好顺序的，大家知道当一个链表排好顺序的时候往里插入是特别困难的，
 * 查找的时候也特别麻烦，因为你得从头去遍历查找这个元素到底在哪里。
 * 跳表的底层是一个链表，我们在这些链表的基础上在拿出一些关键元素来，在上面再做一层，
 * 那这些关键元素的这一层也是一个链表，如果数量特别大的话在这个基础之上在拿一层出来再做一层链表，
 * 每层链表的数据越来越少，在我们查找的时候从顶层往下开始查找。
 */
public class ConcurrentMapDemo {
    public static void main(String[] args) {
//        Map<String, String> map = new ConcurrentHashMap<>();

        Map<String, String> map = new ConcurrentSkipListMap<>();

//        Map<String, String> map = new Hashtable<>();

        Random random = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();

        for( int i = 0; i < ths.length; i++ ){
            ths[i] = new Thread(()->{
                for( int k = 0; k < 10000; k++ ){
                    map.put("a"+ k, "a"+random.nextInt(10000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t->t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start+"ms");
        System.out.println(map.size());
    }
}
