package suanfa;

import java.util.concurrent.Semaphore;

/**
 * @author taosh
 * @create 2020-05-20 14:51
 */
public class H2oDemo1 {
    private Semaphore s1, s2, s3, s4;

    public H2oDemo1() {
        // H线程信号量
        s1 = new Semaphore(2);
        // O线程信号量
        s2 = new Semaphore(1);

        // 反应条件信号量
        s3 = new Semaphore(0);
        s4 = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        s1.acquire();
        s3.release();
        s4.acquire();
        releaseHydrogen.run();
        s1.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        s2.acquire();
        s4.release(2);
        s3.acquire(2);
        releaseOxygen.run();
        s2.release();
    }

    public static void main(String[] args) {
        H2oDemo1 h2o = new H2oDemo1();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    h2o.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    h2o.oxygen(()-> System.out.print("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
