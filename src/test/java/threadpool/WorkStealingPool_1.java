package threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/30
 */
public class WorkStealingPool_1 {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();

        System.out.println("cpu 核心数:"+Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(3000));

        service.execute(new R(2000));
        service.execute(new R(2000));

        // 由于产生的是守护线程，后台线程，主线程不阻塞的话，看不到输出
        System.in.read();
    }

    private static class R implements Runnable {
        int time;
        public R(int i) {
            this.time = i;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}