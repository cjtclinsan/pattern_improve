package list;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/21
 */
public class TicketSell_3 {
    static List<String> ticket = new LinkedList<>();

    static {
        for( int i = 0; i < 1000; i++ ){
            ticket.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for( int i = 0; i < 10; i++ ){
            new Thread(()->{
                while (true){
                    synchronized (ticket){
                        if( ticket.size() <= 0 ){
                            break;
                        }

                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了--"+ticket.remove(0));
                    }

                }
            }).start();
        }
    }
}