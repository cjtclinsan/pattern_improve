package suanfa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author taosh
 * @create 2020-03-07 13:37
 */
class MaxQueue {
    private Deque<Integer> in;
    private Deque<Integer> bigest;

    public MaxQueue() {
        in = new ArrayDeque<>();
        bigest = new ArrayDeque<>();
    }

    public int max_value() {
        return in.isEmpty() ? -1 : bigest.peek();
    }

    //加元素
    public void push_back(int value) {
        in.offer(value);
        //判断大小,  取队尾
        if( !bigest.isEmpty() && bigest.peekLast() < value ){
            bigest.pollLast();
        }
        bigest.offer(value);
    }

    public int pop_front() {
        if( in.isEmpty() ){
            return -1;
        }

        //判断出栈数据与最大数据大小
        int value = in.pop();
        if( bigest.peek() == value ){
            bigest.pop();
        }

        return value;
    }
}
