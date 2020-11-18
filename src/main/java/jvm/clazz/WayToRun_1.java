package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/18
 */
public class WayToRun_1 {
    public static void main(String[] args) {
        //第一次执行 100000 遍，
        for (int i = 0; i < 100000; i++) {
            m();
        }
        // 第二次执行 100000 遍 这个进行了编译
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            m();
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static void m() {
        for (int i = 0; i < 100000; i++) {
            long j = i % 3;
        }
    }
}