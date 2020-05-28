package thread;

/**
 * 对某个对象加锁
 */
public class SynchronizedDemo1 {
    private int count = 10;
    private Object o = new Object();

    public void m1(){
        //当我拿到o的锁的时候才能执行这段代码
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count = " +count);
        }
    }

    public void m2(){
        //不用每次new一个对象出来加锁
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+" count = " +count);
        }
    }

    //等同于在方法代码前加synchronized(this)
    public synchronized void m3(){
        count--;
        System.out.println(Thread.currentThread().getName()+" count = " +count);
    }
}