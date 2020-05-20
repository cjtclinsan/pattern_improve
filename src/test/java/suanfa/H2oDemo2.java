package suanfa;

import java.util.concurrent.Semaphore;

/**
 * @author taosh
 * @create 2020-05-20 15:13
 */
public class H2oDemo2 {
    public H2oDemo2() {
    }

    Semaphore h = new Semaphore(2);
    Semaphore o = new Semaphore(0);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        releaseOxygen.run();
        h.release(2);
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
