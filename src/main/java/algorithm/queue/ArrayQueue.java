package algorithm.queue;

/**
 * @author woshi
 * @date 2020/6/23
 * 用数组实现队列
 */
public class ArrayQueue {
    private String[] items;
    private int n = 0;

    //head 对头下标   tail队尾下标
    private int head = 0;
    private int tail = 0;

    //申请一个大小为capacity的数组
    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean qnqueue(String item){
        //如果tail==n 表示队满
        if(n == tail){
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    //出队
    public String dequeue(){
        //如果tail==head，表示空
        if( tail == head ){
            return null;
        }

        String ret = items[head];
        ++head;
        return ret;
    }
}