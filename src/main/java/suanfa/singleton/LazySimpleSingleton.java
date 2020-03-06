package suanfa.singleton;

/**
 * 外部调用时才进行实例化
 * @author taosh
 * @create 2020-03-04 16:55
 */
public class LazySimpleSingleton {
    private LazySimpleSingleton(){}

    //静态块   公共内存区域
    private static LazySimpleSingleton lazy = null;
    public static LazySimpleSingleton getInstance(){
        if( null == lazy ){
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}
