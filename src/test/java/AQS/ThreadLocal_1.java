package AQS;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class ThreadLocal_1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "yyf";
        }).start();
    }
}

@Data
class Person {
    String name = "longdd";
}