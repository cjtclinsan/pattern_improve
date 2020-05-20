package demo;

/**
 * @author taosh
 * @create 2020-04-21 16:03
 */
public class ThreadLocalDemo {
    /**
     * 每个线程一个，互不干扰
     */
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new ThreadLocalDemo().threadLocalTest();
    }

    public void threadLocalTest() throws InterruptedException {
        //主线程设置
        THREAD_LOCAL.set("tcMain");
        String v = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之前，"+Thread.currentThread().getName()+"线程取到的值："+v);

        new Thread(()->{
           String tv = THREAD_LOCAL.get();
            System.out.println(Thread.currentThread().getName() + "线程取到的值：" + tv);
            //设置threadlocal
            THREAD_LOCAL.set("tcChild");
            tv = THREAD_LOCAL.get();
            System.out.println("重新设置之后，" + Thread.currentThread().getName() + "线程取到的值为：" + tv);
            System.out.println(Thread.currentThread().getName()+"线程执行结束");
        }).start();
        //sleep
        Thread.sleep(1000);
        v = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之后，" + Thread.currentThread().getName() + "线程取到的值：" + v);
    }
}
