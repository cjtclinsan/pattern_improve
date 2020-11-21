package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class CacheLinePadding_2 {
    private static class Padding {
        // 创建一个 7 个 long 类型的数
        public volatile long p1,p2,p3,p4,p5,p6,p7;
    }

    // 从 padding 继承，就会发生我前面先占 56 个字节，然后把我自己的这个存到后面
    // 所以我自己就占了一行，另一个绝对不会在一个行里，效率就高了
    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        // 循环1000000次，让 x 不断产生值，刚好这两个值位于一个缓存行，想办法不让它位于一个缓存行
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_000L; i++) {
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