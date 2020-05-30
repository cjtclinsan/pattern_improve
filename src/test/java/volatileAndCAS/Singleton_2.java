package volatileAndCAS;

/**
 * @author woshi
 * @date 2020/5/30
 *
 */
public class Singleton_2 {
    private static final Singleton_2 INSTANCE;

    private Singleton_2() {}

    static {
        INSTANCE = new Singleton_2();
    }

    public static Singleton_2 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton_2 t1 = Singleton_2.getInstance();
        Singleton_2 t2 = Singleton_2.getInstance();

        System.out.println(t1 == t2);
    }
}