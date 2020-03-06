package suanfa.faced;

/**
 * @author taosh
 * @create 2020-03-06 10:40
 */
public class Faced {
    private SubSystemA a = new SubSystemA();
    private SubSystemB b = new SubSystemB();
    private SubSystemC c = new SubSystemC();

    // 对外接口
    public void doA() { this.a.doA(); }

    // 对外接口
    public void doB() { this.b.doB(); }

    // 对外接口
    public void doC() { this.c.doC(); }
}
