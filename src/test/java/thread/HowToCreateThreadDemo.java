package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author taosh
 * @create 2020-05-27 19:28
 */
public class HowToCreateThreadDemo {
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("Hello World Thread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello World MyRun!");
            lock();
        }

        public static synchronized void lock(){
            while (true){

            }
        }
    }

    static class MyCall implements Callable<String> {
        @Override
        public String call() {
            System.out.println("Hello MyCall");
            return "success";
        }
    }

    //启动线程
    public static void main(String[] args) throws InterruptedException {
        //new MyThread().start();

        //new Thread(new MyRun()).start();

//        new Thread(()->{
//            System.out.println("Hello lambda");
//        }).start();
//
//        Thread t = new Thread(new FutureTask<>(new MyCall()));
//        System.out.println(t.getState());
//        t.start();
//        System.out.println(t.getState());
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()-> System.out.println("Hello ThreadPool"));
//        executorService.shutdown();

        Thread t1 = new Thread(new MyRun());
        Thread t2 = new Thread(new MyRun());

        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println(t2.getState());
    }
}
