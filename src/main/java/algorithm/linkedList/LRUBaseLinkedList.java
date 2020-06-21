package algorithm.linkedList;

import lombok.Data;

/**
 * @author woshi
 * @date 2020/6/20
 *
 * 基于单链表LRU算法
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表容量
     */
    private Integer capacity;

    /**
     * 链表长度
     */
    private Integer length;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data){
        SNode preNode = findPreNode(data);

        //链表中存在，移到链表头
        if( preNode != null ){
            deleteElemOptim(preNode);
            insertElemAtBegin(data);
        }else {
            if(length >= this.capacity ){
                //删除尾结点
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        //空，直接返回
        if( ptr.getNext() == null ){
            return;
        }

        //倒数第二个结点
        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    /**
     * 链表头部插入数据
     * 新建一个结点，放入数据，并将next指针指向第一个头结点的next节点
     * 再将头结点next指向新结点
     * @param data
     */
    private void insertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    /**
     * 删除preNode结点的
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode node = preNode.getNext();
        preNode.setNext(node.getNext());
        node = null;
        length--;
    }

    /**
     * 获取查找到元素的前一个结点
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null){
            if( data.equals(node.getNext().getElement()) ){
                //前一个元素
                return node;
            }
            node = node.getNext();
        }

        return null;
    }

    @Data
    public class SNode<T> {
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
    }
}