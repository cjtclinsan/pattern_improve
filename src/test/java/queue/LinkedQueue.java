package queue;

/**
 * @author woshi
 * @date 2020/6/14
 */
public class LinkedQueue {
    //定义一个节点
    private class Node{
        String value;
        Node next;
    }
    //记录队列元素个数
    private int size = 0;
    //head指向对头节点，tail指向队尾节点
    private Node head;
    private Node tail;

    //申请一个队列
    public LinkedQueue() {}

    //入队
    public boolean enqueue(String item){
        Node newNode = new Node();
        newNode.value = item;
        if(size == 0){
            head = newNode;
        }else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    //出队
    public String dequeue(){
        String res = null;
        if(size == 0){
            return res;
        }
        if(size == 1){
            tail = null;
        }
        res = head.value;
        head = head.next;
        size--;
        return res;
    }
}