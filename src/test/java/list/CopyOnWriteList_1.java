package list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author woshi
 * @date 2020/10/21
 */
public class CopyOnWriteList_1 {
    public static void main(String[] args) {
//        List<String> lists = new ArrayList<>();    //会出现并发问题
//        List<String> lists = new Vector<>();
        List<String> lists = new CopyOnWriteArrayList<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];

        for( int i = 0; i < ths.length; i++ ){
            Runnable task = () -> {
                for(int i1 = 0; i1 < 1000; i1++ ){
                    lists.add("a"+r.nextInt(10000));
                }
            };

            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    private static void runAndComputeTime(Thread[] ths) {
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
        System.out.println(s2-s1);
    }
}