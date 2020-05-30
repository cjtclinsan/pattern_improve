package volatileAndCAS;

/**
 * @author woshi
 * @date 2020/5/30
 * 饿汉式
 * 构造方法设置成private防止外部直接去new
 * 通过getInstance()来访问这个实例，无论调用多少次，本质上只有一个
 * 由JVM来保证
 *
 * 缺点：不管有没有用到，类装载时完成初始化
 */
public class Singleton_1 {
    private static final Singleton_1 INSTANCE = new Singleton_1();

    private Singleton_1() {}

    public static Singleton_1 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton_1 t1 = Singleton_1.getInstance();
        Singleton_1 t2 = Singleton_1.getInstance();

        System.out.println(t1 == t2);
    }
}