package pattern_improve.proxy.demo1;

/**
 * @author taosh
 * @create 2020-03-05 16:14
 */
public class Proxy implements ISubject {
    private ISubject subject;

    public Proxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        subject.request();
        after();
    }

    private void after() {
        System.out.println("called after request().");
    }

    private void before() {
        System.out.println("called before request().");
    }

}
