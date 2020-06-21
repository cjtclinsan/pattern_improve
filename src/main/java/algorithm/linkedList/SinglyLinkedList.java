package algorithm.linkedList;

/**
 * @author woshi
 * @date 2020/6/21
 * 1）单链表的插入，删除，查找
 * 2）假设链表中存储int类型
 */
public class SinglyLinkedList {
    private Node head = null;

    public Node findByValue(int val){
        Node p = head;
        while (p != null && p.data != val){
            p = p.next;
        }

        return p;
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while (p != null && pos != index){
            p = p.next;
            pos++;
        }

        return p;
    }

    //无头结点
    //表头部插入
    //与输入的顺序相反，逆序
    public void insertToHead(int val){
        Node newNode = new Node(val, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode){
        if( head == null ){
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    //顺序插入  链表尾部插入
    public void insertTail(int val){
        Node newNode = new Node(val, null);
        if( head == null ){
            head = newNode;
        }else {
            Node q = head;
            while (q.next != null) {
                q = q.next;

            }
            //插入链表尾
            newNode.next = q.next;
            q.next = newNode;
        }
    }

    //插入p结点之后
    public void insertAfter(Node p, int val){
        Node newNode = new Node(val, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode){
        if(p == null){
            return;
        }

        newNode.next = p.next;
        p.next = newNode;
    }

    //插入p结点之前
    public void insertBefore(Node p, int val){
        Node newNode = new Node(val, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode){
        if(p == null){
            return;
        }

        if( p == head ){
            insertToHead(newNode);
            return;
        }

        Node q = head;
        while ( q != null && q.next != p ){
            q = q.next;
        }

        if( q == null ){
            return;
        }

        newNode.next = p;
        q.next = newNode;
    }

    //删除结点p
    public void deleteByNode(Node p) {
        if(p == null || head == null ){
            return;
        }

        if( head == p ){
            head = head.next;
            return;
        }

        Node q = head;
        while ( q != null && q.next != p ){
            q = q.next;
        }

        if( q == null ){
            return;
        }

        q.next = q.next.next;
    }

    //根据value删除结点
    public void deleteByValue(int value){
        if(head == null){
            return;
        }

        Node p = head;
        Node q = null;
        while (p != null && p.data != value){
            //记录前一个结点
            q = p;
            p = p.next;
        }

        if (p == null) {
            return;
        }

        if (q == null){
            head = head.next;
        }else {
            //删除
            q.next = q.next.next;
        }

        //可重复删除指定value的代码
        if( head != null && head.data == value ){
            head = head.next;
        }
        Node pNode = head;
        while (pNode != null){
            //只要==，就删
            if(pNode.next.data == value){
                pNode.next = pNode.next.next;
                continue;
            }
            pNode = pNode.next;
        }
    }

    //判断true or false
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;

        boolean flag = true;
        while (l != null && r != null){
            if( l.data == r.data ){
                l = l.next;
                r = r.next;
                continue;
            }else {
                flag = false;
                break;
            }
        }

        return flag;
    }

    //判断是否回文
    public boolean palindrome(){
        if( head == null ){
            return false;
        }else {
            //开始找中间结点
            Node p = head;
            Node q = head;

            if( p.next == null ){
                //只有一个元素
                return true;
            }
            while (q.next != null && q.next.next != null){
                p = p.next;
                q = q.next.next;
            }

            System.out.println("中间结点"+p.data);
            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null){
                //p 一定为整个链表的中点，且节点数为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一的节点"+leftLink.data);
                System.out.println("右边第一的节点"+rightLink.data);
            }else {
                //p q均为中点结点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return TFResult(leftLink, rightLink);
        }
    }

    //带结点的链表翻转
    private Node inverseLinkList_head(Node p) {
        //Head 为新建的一个头节点
        Node Head = new Node(9999, null);
        //p 为原来整个链表的头节点，现在Head指向整个链表
        Head.next = p;

        /**
         * 带头结点的链表翻转等价于从第二个结点开始重新头插法建立链表
         */
        Node cur = p.next;
        p.next = null;
        Node next = null;

        //翻转
        while (cur != null){
            next = cur.next;
            cur.next = Head.next;
            Head.next = cur;

            cur = next;
        }

        //返回左半部分的中点之前那个节点
        return Head;
    }

    //无头结点的链表翻转
    public Node inverseLinkList(Node p){
        Node pre = null;
        Node r = head;
        Node next = null;

        while (r != p){
            next = r.next;

            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;

        return r;
    }

    public static Node createNode(int value){
        return new Node(value, null);
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}