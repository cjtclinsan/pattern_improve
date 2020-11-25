package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/24
 */
public class InvokeDynamic {
    public static void main(String[] args) {
        I i = C::n;
        I i2 = C::n;
        I i3 = C::n;
        I i4 = () -> C.n();

        System.out.println(i.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());
        System.out.println(i4.getClass());
    }

    @FunctionalInterface
    public interface I {
        void m();
    }

    public static class C {
        public static void n(){
            System.out.println("hello");
        }
    }
}