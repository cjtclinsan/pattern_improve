package suanfa.proxy.demo2;

/**
 * @author taosh
 * @create 2020-03-05 16:19
 */
public class Zhanglaosan implements IPerson {
    private Zhangsan zhangsan;

    public Zhanglaosan(Zhangsan zhangsan) {
        this.zhangsan = zhangsan;
    }

    @Override
    public void findLove() {
        System.out.println("开始物色");
        zhangsan.findLove();
        System.out.println("开始交往");
    }
}
