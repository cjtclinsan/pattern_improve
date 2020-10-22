package list;

import java.util.PriorityQueue;

/**
 * @author woshi
 * @date 2020/10/22
 */
public class PriorityQueue_1 {
    public static void main(String[] args) {
        PriorityQueue<String> p = new PriorityQueue<>();

        p.add("c");
        p.add("a");
        p.add("e");
        p.add("d");
        p.add("f");

        System.out.println(p);

        for( int i = 0; i <5; i++ ){
            System.out.println(p.poll());
        }

    }
}