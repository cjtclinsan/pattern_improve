package suanfa.proxy.demo2;

/**
 * @author taosh
 * @create 2020-03-05 16:23
 */
public class Test {
    public static void main(String[] args) {
        Zhanglaosan zhanglaosan = new Zhanglaosan(new Zhangsan());
        zhanglaosan.findLove();
    }
}