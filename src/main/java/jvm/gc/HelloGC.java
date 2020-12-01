package jvm.gc;

import java.util.LinkedList;
import java.util.List;

/**
 * @author woshi
 * @date 2020/11/26
 */
public class HelloGC {
    public static void main(String[] args) {
        System.out.println("HelloGC");
        List list = new LinkedList();
        for (;;){
            byte[] bytes = new byte[1024*1024];
            list.add(bytes);
        }
    }
}