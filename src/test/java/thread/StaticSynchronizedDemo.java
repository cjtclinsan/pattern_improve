package thread;

/**
 * @author woshi
 * @date 2020/5/28
 */
public class StaticSynchronizedDemo {
    private static int count = 10;

    //等同于synchronized(StaticSynchronizedDemo.class)  锁住整个类
    public synchronized static void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+" count = " +count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(()-> StaticSynchronizedDemo.m());
            t.start();
        }
    }

}