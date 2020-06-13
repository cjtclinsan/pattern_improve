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
     * 检车环
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