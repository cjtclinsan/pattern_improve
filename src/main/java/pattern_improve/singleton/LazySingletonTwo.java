package pattern_improve.singleton;

/**
 * @author taosh
 * @create 2020-03-04 17:02
 */
public class LazySingletonTwo {
    private LazySingletonTwo(){}

    private static LazySingletonTwo lazy = null;
    public synchronized static LazySingletonTwo getInstance(){
        if( null == lazy ){
            lazy = new LazySingletonTwo();
        }

        return lazy;
    }
}
