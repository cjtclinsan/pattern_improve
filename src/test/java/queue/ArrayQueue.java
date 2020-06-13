package queue;

/**
 * @author woshi
 * @date 2020/6/14
 */
public class ArrayQueue {
    //存储数据的数组
    private String[] items;
    //九路数组容量
    private int n;

    private int size;

    //head记录队头索引，tail记录队尾索引
    private int head = 0;
    private int tail = 0;

    //申请一个指定容量的队列
    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队
     * 队满时入队失败
     * 频繁出入队，造成数组使用不连续
     * 在入队时，集中触发数据搬移
     * 在末尾插入数据，注意tail指向队尾的索引+1
     */
    public boolean enqueue(String item){
        //表示队满
        if(head == 0 && tail == n){
            return false;
        }else if(head != 0 && tail == n){
            //head != 0表示有空闲位置，    表示需要数据搬移
            for (int i = head; i < tail; i++){
                items[i-head] = items[i];
            }
            head = 0;
            tail = tail - head;
        }
        //将数据加入队列
        items[tail++] = item;
        size++;
        return true;
    }

    /**
     * 出队
     * 队空，出队失败
     * head索引+1
     */
    public String dequeue(){
        String res = null;
        if( head == tail ){
            return res;
        }

        res = items[head++];
        size--;
        return res;
    }
}