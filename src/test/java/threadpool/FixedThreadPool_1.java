package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author woshi
 * @date 2020/10/28
 */
public class FixedThreadPool_1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println("单线程耗时:"+ (end-start) + "ms");

        final int cpuCoreNum = 8;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 50000);
        MyTask t2 = new MyTask(50001, 100000);
        MyTask t3 = new MyTask(100001, 150000);
        MyTask t4 = new MyTask(150001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println("并发耗时:" + (end-start) + "ms");
    }

    private static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end ; i++) {
            if( isPrime(i) ){
                results.add(i);
            }
        }
        return results;
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j <= i/2 ; j++) {
            if( i%j == 0 ){
                return false;
            }
        }
        return true;
    }

    private static class MyTask implements Callable<List<Integer>> {
        int start, end;
        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(start, end);
            return r;
        }
    }
}