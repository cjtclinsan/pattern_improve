package pattern_improve.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:44
 */
public class BatterCakeDecorator extends BatterCake {
    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }
}
