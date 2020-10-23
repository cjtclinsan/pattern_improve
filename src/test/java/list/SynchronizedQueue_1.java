package list;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author woshi
 * @date 2020/10/22
 */
public class SynchronizedQueue_1 {
    public static void main(String[] args) throws InterruptedException {
        // 容量为 0
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");     //阻塞等待消费

        strs.put("bbb");
        //strs.add("aaa");        抛异常

        System.out.println(strs.size());
    }
}