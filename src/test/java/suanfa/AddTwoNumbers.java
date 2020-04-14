package suanfa;

import java.util.Stack;

/**
 * @author taosh
 * @create 2020-04-14 10:55
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while ( l1 != null ){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while ( l2 != null ){
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int tmp = 0;
        ListNode res = null;
        while ( !stack1.isEmpty() || !stack2.isEmpty() || tmp > 0 ){
            int sum = tmp;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();

            ListNode node = new ListNode(sum % 10);
            node.next = res;
            res = node;
            tmp = sum / 10;
        }

        return res;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        System.out.println("--------");

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNumbers(l1, l2);
    }
}
