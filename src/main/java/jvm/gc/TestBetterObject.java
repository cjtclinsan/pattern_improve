package jvm.gc;

/**
 * @author woshi
 * @date 2020/12/2
 */
public class TestBetterObject {
    public static void main(String[] args) {
        Object o = new Object();
        for (int i = 0; i < 100; i++) {
            o = new Object();
        }

        for (int i = 0; i < 100; i++) {
            Object object = new Object();
        }
    }
}