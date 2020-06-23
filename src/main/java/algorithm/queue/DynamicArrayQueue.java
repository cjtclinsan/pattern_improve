package algorithm.queue;

/**
 * @author woshi
 * @date 2020/6/23
 * 动态数组
 */
public class DynamicArrayQueue {
    private String[] items;
    private int n = 0;

    private int head = 0;
    private int tail = 0;

    public DynamicArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    //入队  将item放入队尾
    public boolean enqueue(String item){
        //表示队尾没有空间了
        if( tail == n ){
            //tail == n && head == 0表示整个空间都占满了
            if( head == 0 ){
                return false;
            }
            //数据搬移   往前搬
            for( int i = head; i < tail; ++i ){
                items[i-head] = items[i];
            }

            //更新 head和tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;
    }

    //出队
    public String dequeue(){
        //如果head == tail 表示队列空
        if (head == tail){
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;

    }
}