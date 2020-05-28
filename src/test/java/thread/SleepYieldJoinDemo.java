package thread;

/**
 * @author taosh
 * @create 2020-05-28 9:05
 */
public class SleepYieldJoinDemo {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    /**
     * Sleep的意思就是睡眠，让当前线程暂停一段时间让别的线程去运行。
     * 睡眠时间到了自动复活
     */
    static void testSleep(){
        new Thread(()->{
            for( int i = 0; i < 100; i++ ){
                System.out.println("A"+i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * yield,就是当前线程正在执行的时候停下来进入等待队列，
     * 在系统的调度算法里依然有可能把刚加入队列的线程拿回来继续执行
     * 当然更大的可能性是那原来在里面等待的线程
     * 所以yield一下，意思就是让当前线程出cpu时间片，后面能不能抢到就不管了
     */
    static void testYield() {
        new Thread(() -> {
            for( int i = 0; i < 100; i++ ){
                System.out.println("A"+i);

                if( i % 10 == 0 ){
                    Thread.yield();
                }
            }
        }).start();

        new Thread(()->{
            for( int i = 0; i < 100; i++ ){
                System.out.println("---B"+i);
                if ( i % 10 == 0 ){
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * join意思就是在自己当前线程加入join的线程，当前线程等待。
     * 等调用的线程运行完了，当前线程再去执行。
     * t1和t2两个线程，在t1的某个点上调用了t2.join(),他就会跑到t2上去执行
     * t1会等待t2执行完毕后继续执行（自己join自己没意义）
     */
    static void testJoin(){
        Thread t1 = new Thread(()->{
            for( int i = 0; i < 10; i++ ){
                System.out.println("A"+i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for( int i = 0; i < 10; i++ ){
                System.out.println("B"+i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for( int i = 10; i < 20; i++ ){
                System.out.println("B"+i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
