package jucTool;

import java.util.concurrent.Semaphore;

/**
 * @author woshi
 * @date 2020/6/3
 * 信号灯
 * s.acquire()  阻塞方法   acquire不到的话就停在这里
 * 如果Semaphore s = new Semaphore(1),acquire一下就变成0，变成0之后别人acquire不到。编程结束之后release()，把0变成1，还原化
 * 限流：比如Semaphore(5)就是一共有5许可，acquire就是获得这把锁，线程如果想要继续执行，必须从Semaphore里面获得一个许可，一共五个，用到0个就必须等待
 *
 * 默认Semaphore是非公平的，new Semaphore(2, true)  第二个值传true就是设置公平。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
 //       Semaphore s = new Semaphore(4);
        Semaphore s = new Semaphore(2, true);

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("t1 running start...");
                Thread.sleep(500);
                System.out.println("t1 running end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("t2 running start...");
                Thread.sleep(500);
                System.out.println("t2 running end...");
                s.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("t3 running start...");
                Thread.sleep(500);
                System.out.println("t3 running end...");
                s.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("t4 running start...");
                Thread.sleep(500);
                System.out.println("t4 running end...");
                s.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }).start();
    }
}