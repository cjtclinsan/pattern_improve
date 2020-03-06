package pattern_improve.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:45
 */
public class EggBatterCake extends BatterCakeDecorator {
    public EggBatterCake(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getMsg(){
        return super.getMsg()+"+1个鸡蛋";
    }

    @Override
    protected int getPrice(){
        return super.getPrice()+1;
    }
}
