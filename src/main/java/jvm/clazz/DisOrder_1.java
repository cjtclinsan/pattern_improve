package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/20
 */
public class DisOrder_1 {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;){
            i++;
            // 初始化
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            // 顺序执行的话会产生 0,1 1,0  1,1 不会有 0,0
            Thread one = new Thread(()->{
                // 由于线程 one 先启动，下面让它等一等
                shortwait(100000);
                a = 1;
                x = b;
            });

            // 第二个线程 先执行 b = 1,y = a
            Thread two = new Thread(()->{
                shortwait(100000);
                b = 1;
                y = a;       // 有可能是1 有可能是 0
            });

            one.start();
            two.start();
            one.join();
            two.join();

            System.out.println(i);
            String result = "第" + i + "次（" + x + "," + y + ")";
            // 只要有一次 0,0 则一定发生了重排
            if( x == 0 && y == 0 ){
                System.out.println(result);
                break;
            }else {

            }
        }
    }

    private static void shortwait(int interval) {
        long start = System.nanoTime();
        long end;

        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}