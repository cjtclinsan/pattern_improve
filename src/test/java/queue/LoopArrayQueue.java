package queue;

/**
 * @author woshi
 * @date 2020/6/14
 */
public class LoopArrayQueue {
    private String[] items;
    private int n;
    private int size = 0;
    //head记录头索引，tail记录尾索引
    private int head = 0;
    private int tail = 0;

    public LoopArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    //入队  判断队满
    public boolean enqueue(String item){
        //队满
        if((tail +1) % n == head)
            return false;
        items[tail] = item;
        //循环
        tail = (tail + 1) % n;
        size++;
        return true;
    }

    //出队  判断队空
    public String dequeue(){
        String res = null;
        //队空
        if(head == tail){
            return null;
        }
        res = items[head];
        head = (head + 1) % n;
        size--;
        return res;
    }
}