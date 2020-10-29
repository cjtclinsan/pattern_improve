package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author woshi
 * @date 2020/10/28
 */
public class SingleThreadPool_1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()-> System.out.println(j + " " + Thread.currentThread().getName()));
        }
    }
}