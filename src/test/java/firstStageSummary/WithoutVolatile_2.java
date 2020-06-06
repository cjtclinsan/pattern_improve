package firstStageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/5
 * 在程序1的基础上修改：添加volatile修饰List集合，实现线程之间的信息传递，但还是有不足之处
 * volatile一定要尽量去修饰普通的值，不要去修饰引用的值，因为volatile修饰引用类型，这个引用对象指向的是另外一个new出来的对象，
 * 如果这个对象里边的成员变量的值改变了，是无法观察到的
 */
public class WithoutVolatile_2 {
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        WithoutVolatile_2 t = new WithoutVolatile_2();
        new Thread(()->{
            for(int i = 0; i < 10; i++){
                t.add(new Object());
                System.out.println("add:"+i);

//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }, "t1").start();

        new Thread(()->{
            while (true){
                if( t.size() == 5 ){
                    System.out.println("t2结束");
                    break;
                }
            }
        }, "t2").start();
    }
}