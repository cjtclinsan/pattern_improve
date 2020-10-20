package reference;

import java.lang.ref.WeakReference;

/**
 * @author woshi
 * @date 2020/10/20
 */
public class WeakReference_1 {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());

        System.gc();

        System.out.println(m.get());

        ThreadLocal<M> t = new ThreadLocal<>();
        t.set(new M());
        t.remove();
    }
}