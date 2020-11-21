package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class ClassReLoading_1 {
    public static void main(String[] args) throws ClassNotFoundException {
        TcClassLoader classLoader = new TcClassLoader();
        Class clazz = classLoader.loadClass("jvm.clazz.Hello");

        classLoader = null;
        System.out.println(clazz.hashCode());

        // 将第一个 classloader 设为 null
        classLoader = null;

        // new一个新的 classloader
        classLoader = new TcClassLoader();
        Class clazz1 = classLoader.loadClass("jvm.clazz.Hello");
        System.out.println(clazz1.hashCode());

        // 两个 class 是否相等？true 是因为双亲委派，它会让父亲去找
        System.out.println(clazz == clazz1);
    }
}