package cas;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/1
 * 可重入锁
 * 锁了一下，还可以对同样的这把锁再锁一下
 * synchronized必须是可重入的，不然子类
 *
 * 本例中由m1锁定this，只有m1执行完毕的时候，m2才执行
 */
public class ReentrantLock_1 {
    synchronized void m1(){
        for(int i = 0; i < 10; i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if( i == 2 ){
                m2();
            }
        }
    }

    synchronized void m2() {
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        ReentrantLock_1 t = new ReentrantLock_1();
        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}