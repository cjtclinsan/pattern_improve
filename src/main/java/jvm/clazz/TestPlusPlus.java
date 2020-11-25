package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/23
 */
public class TestPlusPlus {
    public static void main(String[] args) {
        int i = 100;
    }

    public void m1(){
        int i = 200;
    }

    public void m2(int k){
        int i = 300;
    }

    public void add(int a, int b){
        int c = a + b;
    }

    public void m3(){
        Hello hello = new Hello();
        int i = hello.m(3);
    }
}