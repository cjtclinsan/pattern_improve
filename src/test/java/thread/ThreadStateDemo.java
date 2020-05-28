package thread;

/**
 * @author taosh
 * @create 2020-05-28 13:44
 */
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread thread = new Thread(()->{
            System.out.println(main.getState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(main.getState());
        });

        thread.start();
        thread.join();
    }
}
