package linkedlist;

/**
 * @author woshi
 * @date 2020/6/12
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.next = node1;
        node1.next = node2;
        node2.next = head;

        System.out.println(checkCircle(head));

    }
    /**
     * 单链表反转
     * @param list
     */
    public static Node reverse(Node list){
        if (list == null)
            return list;
        Node curr = list, pre = null;
        while (curr != null){
            Node next = curr.next;
            curr.next = pre;

            //指针下移
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 检测环
     * @param list
     */
    public static boolean checkCircle(Node list){
        if(list == null)
            return false;

        Node fast = list.next;
        Node slow = list;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if( slow == fast ){
                return true;
            }
        }

        return false;
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    /**
     * 有序链表合并
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        //利用哨兵简化难度
        ListNode solider = new ListNode(0);
        ListNode p = solider;

        while (l1 != null && l2 != null){
            if( l1.val < l2.val ){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if( l1.next != null ){
            p.next = l1;
        }
        if( l2.next != null ){
            p.next = l2;
        }

        return solider.next;
    }

    /**
     * 删除倒数第K个节点
     * @param list
     * @param k
     */
    public static Node deleteLastKth(Node list, int k){
        Node fast = list;
        int i = 1;
        //fast先往前走k步
        while (fast != null && i < k){
            fast = fast.next;
            ++i;
        }

        if( fast == null ){
            return list;
        }

        //slow与fast同时往前走，当fast到头时，slow在倒数第k个
        Node slow = list;
        Node prev = null;
        while (fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        //list长度为k
        if( prev == null ){
            list = list.next;
        }else {
            //删除
            prev.next = prev.next.next;
        }

        return list;
    }

    /**
     * 求中间节点
     * @param list
     */
    public static Node findNiddleNode(Node list){
        if( list == null ){
            return null;
        }

        Node fast = list;
        Node slow = list;

        while (fast != null && slow != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    public static void printAll(Node list){
        Node p = list;
        while (p != null){
            System.out.print(p.data+" ");
            p = p.next;
        }
        System.out.println();
    }

    public static Node createNode(int data){
        return new Node(data, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }
}