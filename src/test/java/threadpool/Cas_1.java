package threadpool;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class Cas_1 {

    enum ReadyToRun {T1, T2}

    //保证线程课件
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        new Thread(()->{
            for (char c : c1) {
                while ( r != ReadyToRun.T1){}
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(()->{
            for (char c : c2) {
                while ( r != ReadyToRun.T2){}
                System.out.println(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}