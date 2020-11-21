package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class Singleton {
    private static volatile Singleton INSTANCE;

    private Singleton (){}

    public static Singleton getInstance(){
        if( INSTANCE == null ){
            synchronized (Singleton.class){
                if( INSTANCE == null ){
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}