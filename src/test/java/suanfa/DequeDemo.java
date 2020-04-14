package suanfa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author taosh
 * @create 2020-03-07 14:14
 */
public class DequeDemo {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);

        deque.pollLast();
        System.out.println(deque);
    }
}
