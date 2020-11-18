package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/17
 */
public class ClassLoaderLevel_1 {
    public static void main(String[] args) {
        // 查看是谁 load 到内存的，执行结果是 null，Bootstrap 是使用 c++ 实现的，Java 里没有 class 和他对应
        System.out.println(String.class.getClassLoader());

        // 核心库中某个包里的类执行器，执行结果是null，因为它也是被 Bootstrap 类加载器加载的
        System.out.println(sun.awt.HKSCS.class.getClassLoader());

        // 这个类是位于 ext 目录下的某个 jar 文件里面，执行结果就是 sun.misc.launcher$ExtClassLoader
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());

        // 我们自定义的 ClassLoader 加载器，它是由 sun.misc.Launcher$AppClassLoader 加载
        System.out.println(ClassLoaderLevel_1.class.getClassLoader());

        // 它是一个 Ext 的 ClassLoader 调用它的 getClass()，它本身就是一个 class，然后调用它的 getClassLoader，这个 ClassLLoader 的
        // ClassLoader 是我们最顶级的 ClaaLoaderBootstrap，所以执行结果为 null
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());

        System.out.println(ClassLoaderLevel_1.class.getClassLoader().getClass().getClassLoader());
    }
}