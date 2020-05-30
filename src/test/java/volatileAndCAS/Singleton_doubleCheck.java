package volatileAndCAS;

/**
 * @author woshi
 * @date 2020/5/30
 * 这边就要涉及到volatile了
 * 下面的代码叫做双重检查单例，在前一段代码的基础上，再加一次校验：确实能保证线程安全的问题，
 * 好像感觉不加volatile也不会有问题，其实会出现指令重排序的问题。
 * 比如第一个线程进行初始化  INSTANCE= new Singleton_doubleCheck()这一步其实经过我们编译器编译之后，指令分成了三步
 * 1、给指令申请内存
 * 2、给成员变量初始化
 * 3、把这块内存的内容赋值给INSTANCE
 * 如果没加volatile，2和3指令时可以重排序的，就是先把值赋给INSTANCE，然后进行成员变量初始化，
 * 会导致线程二判断INSTANCE已经存在，就去获取使用，而其实INSTANCE中的变量值还没有赋值
 * 加了volatile指令重排序就被禁止 lock 指令，保证了一定是初始化完了之后才会赋值给INSTANCE
 *
 * JVM里规定了八种原则，其除此之外都是可以指令重排序的
 */
public class Singleton_doubleCheck {
    private static volatile Singleton_doubleCheck INSTANCE;
    private Singleton_doubleCheck(){}
    public static Singleton_doubleCheck getInstance(){
        if( null == INSTANCE ){
            synchronized ( Singleton_doubleCheck.class ){
                if( null == INSTANCE ){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton_doubleCheck();
                }
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for( int i = 0; i < 10; i++ ){
            new Thread(()->{
                System.out.println(Singleton_doubleCheck.getInstance().hashCode());
            }).start();
        }
    }
}