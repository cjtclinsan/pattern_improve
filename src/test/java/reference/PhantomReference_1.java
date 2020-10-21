package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author woshi
 * @date 2020/10/20
 */
public class PhantomReference_1 {
    private static final List<Object> list = new LinkedList<>();
    private static final ReferenceQueue<M> queue = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), queue);
        new Thread(()->{
           while (true){
               list.add(new byte[1024*1024]);

               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   Thread.currentThread().interrupt();
               }

               System.out.println(phantomReference.get());
           }
        }).start();

        new Thread(()->{
            while (true) {
                Reference<? extends M> poll = queue.poll();
                if( poll != null ){
                    System.out.println("---虚引用对象被 JVM 回收了---" + poll);
                }

            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}