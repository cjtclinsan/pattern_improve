package list;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author woshi
 * @date 2020/10/22
 */
public class ConCurrentQueue_1 {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedDeque<>();

        for( int i = 0; i < 10; i++ ){
            strs.offer("a"+i);
        }

        System.out.println(strs);
        System.out.println(strs.size());

        // 取出并 remove
        System.out.println(strs.poll());
        System.out.println(strs.size());

        // 取出
        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}