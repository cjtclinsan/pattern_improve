package AQS;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class ThreadLocal_2 {
    static ThreadLocal<Person> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t1.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Person());
        }).start();
    }
}