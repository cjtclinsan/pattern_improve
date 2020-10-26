package threadpool;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author woshi
 * @date 2020/10/24
 */
public class TransferQueue_1 {
    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        TransferQueue<Character> queue = new LinkedTransferQueue<>();
        new Thread(()->{
            for (char c : c1) {
                try {
                    System.out.println(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(()->{
            for (char c : c2) {
                try {
                    queue.transfer(c);
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}