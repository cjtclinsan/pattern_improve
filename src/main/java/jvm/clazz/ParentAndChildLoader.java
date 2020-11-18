package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/18
 */
public class ParentAndChildLoader {
    public static void main(String[] args) {
        // sun.misc.Launcher$AppClassLoader@18b4aac2     AppClassLoader
        System.out.println(ParentAndChildLoader.class.getClassLoader());

        // null
        System.out.println(ParentAndChildLoader.class.getClassLoader().getClass().getClassLoader());

        // sun.misc.Launcher$ExtClassLoader@6f94fa3e ExtClassLoader
        System.out.println(ParentAndChildLoader.class.getClassLoader().getParent());

        // null
        System.out.println(ParentAndChildLoader.class.getClassLoader().getParent().getParent());

        // NullPointerException
        System.out.println(ParentAndChildLoader.class.getClassLoader().getParent().getParent().getParent());
    }
}