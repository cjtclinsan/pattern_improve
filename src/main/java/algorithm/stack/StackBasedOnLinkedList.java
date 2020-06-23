package algorithm.stack;

/**
 * @author woshi
 * @date 2020/6/23
 * 基于链表实现栈
 */
public class StackBasedOnLinkedList {
    private Node top = null;

    public void push(int value){
        Node newNode = new Node(value, null);
        //判断是否为空
        if( top == null ){
            top = newNode;
        }else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * -1代表没有数据
     */
    public int pop(){
        if(top == null){
            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }
    }
}