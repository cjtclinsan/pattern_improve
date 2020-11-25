package jvm.gc;

/**
 * @author woshi
 * @date 2020/11/25
 */
public class TestTLAB {
    User u;
    class User {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    void alloc(int i){
        u = new User(i, "name"+i);
        //new User(i, "name"+i);
    }

    public static void main(String[] args) {
        TestTLAB t = new TestTLAB();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000_0000; i++) {
            t.alloc(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}