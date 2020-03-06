package pattern_improve.proxy.demo1;

/**
 * @author taosh
 * @create 2020-03-05 16:13
 */
public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("real service is called.");
    }
}
