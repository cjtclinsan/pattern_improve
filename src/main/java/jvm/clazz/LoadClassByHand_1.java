package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/18
 */
public class LoadClassByHand_1 {
    public static void main(String[] args) throws ClassNotFoundException {
        //首先拿到 app 这个类加载器之后调用它的 loadClass 方法，接下来把需要加载的类的名字传进去
        Class clazz = LoadClassByHand_1.class.getClassLoader().loadClass("jvm.clazz.ClassLoaderLevel_1");
        System.out.println(clazz.getName());
        // 利用类加载器加载资源
        LoadClassByHand_1.class.getClassLoader().getResourceAsStream("");
    }
}