package suanfa.thread.demo1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author taosh
 * @create 2020-03-04 10:51
 */
public class Test2 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        AtomicInteger index = new AtomicInteger();
        WriteThread writeThread1 = new WriteThread(3, "a", index, 0, 3, arrayList);
        WriteThread writeThread2 = new WriteThread(3, "l", index, 1, 3, arrayList);
        WriteThread writeThread3 = new WriteThread(3, "i", index, 2, 3, arrayList);

        for ( int i = 0 ; i < 3; i++ ){
            writeThread1.run();
            writeThread2.run();
            writeThread3.run();
        }

        System.out.println(arrayList);
    }
}
