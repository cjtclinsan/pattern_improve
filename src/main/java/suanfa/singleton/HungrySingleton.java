package suanfa.singleton;

/**
 * @author taosh
 * @create 2020-03-04 16:38
 */
public class HungrySingleton {
    //先静态，后动态
    //先属性，后方法
    //先上后下
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return new HungrySingleton();
    }
}
