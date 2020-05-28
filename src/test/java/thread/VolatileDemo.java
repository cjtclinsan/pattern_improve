package thread;

/**
 * @author woshi
 * @date 2020/5/28
 */
public class VolatileDemo implements Runnable{
    private /*volatile*/ int count = 100;

    /**
     * synchronized既保证原子性，也保证可见性
     */
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" count = " +count);
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        for( int i = 0; i < 100; i++  ){
            new Thread(demo, "Thread"+i).start();
        }
    }
}