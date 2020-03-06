package suanfa.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:45
 */
public class SauageBatterCake extends BatterCakeDecorator {
    public SauageBatterCake(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {

    }

    @Override
    protected String getMsg(){
        return super.getMsg()+"+1个香肠";
    }

    @Override
    protected int getPrice(){
        return super.getPrice()+2;
    }
}
