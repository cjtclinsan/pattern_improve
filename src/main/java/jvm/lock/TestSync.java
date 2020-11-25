package jvm.lock;

/**
 * @author woshi
 * @date 2020/11/21
 */
public class TestSync {
    synchronized void m(){

    }

    void n(){
        synchronized (this){

        }
    }

    public static void main(String[] args) {

    }
}