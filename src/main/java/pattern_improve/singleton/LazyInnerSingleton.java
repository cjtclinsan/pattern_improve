package pattern_improve.singleton;

/**
 * 兼顾饿汉单例的内存浪费问题和synchronized的性能问题
 * @author taosh
 * @create 2020-03-04 17:11
 */
public class LazyInnerSingleton {
    /**
     * 使用LazyInnerClassGeneral的时候，默认先初始化内部类
     */
    private LazyInnerSingleton(){
        if( LazyHolder.LAZY != null ){
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * static 为了使单例的空间共享，final保证不被重写，重载
     */
    public static final LazyInnerSingleton getInstance(){
        //在返回结果之前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    private static class LazyHolder{
        private static final LazyInnerSingleton LAZY = new LazyInnerSingleton();
    }
}
