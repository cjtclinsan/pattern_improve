package list;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class TransferQueue_1 {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println("1:"+strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");        //阻塞等待叫醒
        strs.put("ccc");
//        strs.put("bbb");

        new Thread(()->{
            try {
                System.out.println("2:"+strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}