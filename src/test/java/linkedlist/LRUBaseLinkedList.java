package linkedlist;

import java.util.Scanner;

/**
 * @author woshi
 * @date 2020/6/12
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头节点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer lenght;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.lenght = 0;
        this.capacity = capacity;
    }

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.lenght = 0;
    }

    /**
     * 增加数据
     */
    public void add(T data){
        SNode preNode = findPreNode(data);

        //链表中已存在改数据
        if( preNode != null ){
            //删除该结点
            deleteElemOptim(preNode);
            //在头结点插入数据
            insertElemAtBegin(data);
        }else {
            //链表中不存在改数据
            if ( lenght >= this.capacity ){
                //链表已满，删除尾结点
                deleteElemEnd();
            }
            insertElemAtBegin(data);
        }
    }

    /**
     * 删除尾结点
     */
    private void deleteElemEnd() {
        SNode ptr = headNode;
        //空链表直接返回
        if( ptr.getNext() == null ){
            return;
        }

        //找到倒数第二个节点
        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }

        //删除
        deleteElemOptim(ptr);
    }

    /**
     * 数据插入头节点
     * @param data
     */
    private void insertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        lenght++;
    }

    /**
     * 删除preNode节点的下一个元素（preNode指向下下个节点）
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        lenght--;
    }

    /**
     * 找到上一个节点
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null){
            if( data.equals(node.getNext().getElement()) ){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    //打印
    private void printAll(){
        SNode node = headNode.getNext();
        while( node != null ){
            System.out.print(node.getElement()+",");
            node = node.getNext();
        }
        System.out.println();
    }

    class SNode<T>{
        private T element;
        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner sc = new Scanner(System.in);
        while (true){
            list.add(sc.nextInt());
            System.out.println(list.lenght);
            list.printAll();
        }
    }
}