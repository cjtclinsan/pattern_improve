package pattern_improve.proxy.demo3;

import pattern_improve.proxy.demo2.IPerson;
import pattern_improve.proxy.demo2.Zhangsan;

/**
 * @author taosh
 * @create 2020-03-05 16:36
 */
public class Test {
    public static void main(String[] args) {
        JdkMeipo meipo = new JdkMeipo();
        IPerson zhangsan = meipo.getInstance(new Zhangsan());
        zhangsan.findLove();

        IPerson lisi = meipo.getInstance(new Lisi());
        lisi.findLove();
    }

}
