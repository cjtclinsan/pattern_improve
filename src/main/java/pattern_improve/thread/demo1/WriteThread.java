package pattern_improve.thread.demo1;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有3个线程和1个公共的字符数组。线程1的功能就是向数组输出A，
 * 线程2的功能就是向字符输出l，线程3的功能就是向数组输出i。
 * 要求按顺序向数组赋值AliAliAli，Ali的个数由线程函数1的参数指定。
 * @author taosh
 * @create 2020-03-04 10:41
 */
public class WriteThread extends Thread{
    //打印次数
    private int times;
    //线程名称
    private String name;
    //顺序变量
    private AtomicInteger index;
    //当前线程的标识
    private int flag;
    //所有线程数
    private int all;
    //存放结果
    private List<String> array;

    public WriteThread(int times, String name, AtomicInteger index, int flag, int all, List<String> array) {
        this.times = times;
        this.name = name;
        this.index = index;
        this.flag = flag;
        this.all = all;
        this.array = array;
    }

    @Override
    public void run() {
        int count = 0;
        System.out.println(count+"   "+times);
        while ( count < times ){
            //判断是否轮到自己
            System.out.println(index.get()+"   "+flag);
            if( index.get() == flag ){
                count++;
                array.add(name);
                //指定下一个是谁
                index.set((flag+1) % all);
                System.out.println(index.get());
            }else {
                count++;
                //指定下一个是谁
                index.set((flag+1) % all);
            }
        }

    }
}
