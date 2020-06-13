package stack;

/**
 * @author woshi
 * @date 2020/6/13
 */
public class LinkedStack<Item> {
    private class Node{
        Item item;
        Node next;
    }

    private Node first;
    private int N;

    //构造器
    public LinkedStack() {
    }

    //添加
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    //删除
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    //是否为空
    public boolean isEmpty(){
        return N == 0;
    }

    //元素数量
    public int size(){
        return N;
    }

    //返回栈中最近添加的元素而不删除
    public Item peek(){
        return first.item;
    }
}