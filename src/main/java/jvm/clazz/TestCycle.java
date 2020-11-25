package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/24
 */
public class TestCycle {
    public static void main(String[] args) {
        TestCycle t = new TestCycle();
        t.m(3);
    }

    public int m(int i){
        if( i == 1 ){
            return 1;
        }
        return i * m(i-1);
    }
}