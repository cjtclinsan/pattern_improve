package pattern_improve.proxy.demo1;

/**
 * @author taosh
 * @create 2020-03-05 16:15
 */
public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}
