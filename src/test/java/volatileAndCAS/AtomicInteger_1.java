package volatileAndCAS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author woshi
 * @date 2020/5/31
 * cas号称无锁优化，或者叫自旋
 * 我们通过Atomic类（原子的）。有一些特别常见的类，老是来回加锁，所以干脆Java就提供这些常见操作的类，内部自带锁
 * 然而这些锁不是synchronized重量级锁，而是用cas操作来实现的
 *
 * AtomicInteger里面包含了一个int类，这个int类自增count++是线程安全的
 *
 * 本身是原子性的，但不能保证多个方法连续调用都是原子性的
 */
public class AtomicInteger_1 {
    AtomicInteger count = new AtomicInteger(0);
    //volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            //调用getAndAddInt -> compareAndSwapInt
            //比较并交换：比如我想改变一个值，把它变成1，但我想做到线程安全，就只能加锁synchronized，不然线程不安全。
            //现在可以用另一把锁来代替synchronized，就是cas操作，里面有三个参数：(V, Expected, newValue)
            //  if( V == E) V = newValue
            //  otherwise try again or fail
            // V:要改的那个值；Expected：第二个参数是期望当前的这个值会是几；NewValue要设定的新值
            // Expected如果对上了期望值，NewValue才会对其进行修改。
            // 当你判断的时候，发现我是期望值，还没有进行新值设定的时候值发生了改变。
            // cas是cpu的原语支持，也就是说cas操作是cpu指令级别的支持，中间不能被打断
            //
            // ABA问题:
            // 一个线程想把某个值从 1 变成 2，通过cas判断期望值是1，准备变成2，在这个过程中，中间有一个线程把值变成了3又变成了1，中间值改过，
            // 但不会影响我cas下面的操作，这就是ABA问题
            // 这个问题怎么解决，如果是int类型，最终值是你期望的，也没什么关系。
            // 如果你确实想管这个问题，可以加版本号，线程对其做任何的修改，修改完以后版本号+1，检查的时候连带版本号一起检查
            //
            count.incrementAndGet();
//            count++;
        }
    }

    public static void main(String[] args) {
        AtomicInteger_1 t = new AtomicInteger_1();

        List<Thread> threads = new ArrayList<>();

        for( int i = 0; i< 10; i++ ){
            threads.add(new Thread(t::m, "t-"+i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}