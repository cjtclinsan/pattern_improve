package volatileAndCAS;

/**
 * @author woshi
 * @date 2020/5/30
 * 上面synchronized直接加在整个方法上，使得同步方法太长了。说不定这个方法里面的有些业务逻辑并不需要加锁，代码能锁少就锁得少一点
 * 那么就先别加锁，先去判断是否为空，如果是空就加锁初始化，这样锁粒度会更细（锁优化的一部分，使得锁粒度更新）
 * 但是还是会出现一个的问题：就是第一个线程判断为空以后，还未进行初始化，第二线程就进来了，此时第一个线程加锁初始化，完了第二个线程再加锁初始化。
 */
public class Singleton_4 {
    private static Singleton_4 INSTANCE;
    private Singleton_4(){}
    public static Singleton_4 getInstance(){
        if( null == INSTANCE ){
            synchronized ( Singleton_4.class ){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton_4();
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for( int i = 0; i < 10; i++ ){
            new Thread(()->{
                System.out.println(Singleton_4.getInstance().hashCode());
            }).start();
        }
    }
}