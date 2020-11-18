package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/18
 */
public class ClassLazyLoading {
    public static void main(String[] args) throws ClassNotFoundException {
        //没有 new 没有访问不会加载
        //P p;
        // new 了被加载
        //X x = new X();
        // 打印 final 值不需要加载整个类
        System.out.println(P.i);
        // 非 final 的会加载
        System.out.println(P.j);
        // 会被加载
        Class.forName("jvm.clazz.ClassLazyLoading$P");
    }

    // 需要用这个类的时候才被加载
    public static class P {
        final static int i = 8;
        static int j = 9;
        static {
            // 只要被加载过，p 就会被打印
            // classload 分为三个步骤，这个过程中会执行静态代码块语句
            System.out.println("p");
        }
    }

    public static class X extends P {
        static {
            System.out.println("x");
        }
    }
}