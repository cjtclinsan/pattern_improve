package volatileAndCAS;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/5/30
 * 懒汉式
 * 先判断INSTANCE == null
 * 带来了线程安全问题
 * 通过加synchronized的方法解决，但是性能会下降
 */
public class Singleton_3 {
    private static Singleton_3 INSTANCE;

    private Singleton_3() {}

    //通过加synchronized的方法解决，但是性能会下降
    public static synchronized Singleton_3 getInstance(){
        if( null == INSTANCE ){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new Singleton_3();
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for( int i = 0; i < 10; i++ ){
            new Thread(()->{
                System.out.println(Singleton_3.getInstance().hashCode());
            }).start();
        }
    }
}