package threadpool;

import java.util.concurrent.*;

/**
 * @author woshi
 * @date 2020/10/28
 */
public class RejectedHandler_1 {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new MyHandler());
        for (int i = 0; i < 10000; i++) {
            service.execute(()->{
                System.out.println(123);
            });
        }
    }

    private static class MyHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // save r to mysql,kafka,redis
            if( executor.getQueue().size() > 2 ){
                System.out.println("queue");
            }
        }
    }
}