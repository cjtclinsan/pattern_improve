package thread;

/**
 * @author taosh
 * @create 2020-05-28 13:44
 */
public class ThreadStateDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread thread = new Thread(()->{
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(main.getState());
        });

        thread.start();
        Thread.sleep(1000);

        System.out.println(thread.getState());
    }
}
