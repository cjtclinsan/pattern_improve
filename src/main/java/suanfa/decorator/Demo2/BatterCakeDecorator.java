package suanfa.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:44
 */
public abstract class BatterCakeDecorator extends BatterCake {
    //静态代理  委派
    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }
    protected abstract void doSomething();

    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }
}
