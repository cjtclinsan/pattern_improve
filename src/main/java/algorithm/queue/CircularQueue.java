package algorithm.queue;

/**
 * @author woshi
 * @date 2020/6/23
 * 循环队列
 */
public class CircularQueue {
    //数组:items，数组大小:n
    private String[] items;
    private int n = 0;
    //head tail
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean enqueue(String item){
        //队列满
        if( (tail + 1) % n == head ){
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    //出队
    public String dequeue(){
        //如果head==tail 为空
        if( head == tail ){
            return null;
        }

        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}