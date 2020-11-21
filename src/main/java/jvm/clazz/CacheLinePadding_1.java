package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class CacheLinePadding_1 {
    private static class T {
        // 创建一个类里面只有一个 long 类型
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    // 静态初始化完成之后，内存里有两个数组，这两个数组里面指向的是 new 来的对象，里面只有一个 8 字节的 long类型
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    // 启动两个线程
    public static void main(String[] args) throws InterruptedException {
        // 第一个线程循环1000000次，让 x 值发生变化，刚好这两个值位于一个缓存行，又正好在一个 CPU
        // 就会发生第一个 CPU 和 第二个 CPU 不断更新缓存行
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.nanoTime() -start);
    }

}