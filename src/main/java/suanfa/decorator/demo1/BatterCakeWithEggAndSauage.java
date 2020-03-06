package suanfa.decorator.demo1;

/**
 * @author taosh
 * @create 2020-03-04 21:41
 */
public class BatterCakeWithEggAndSauage extends BatterCakeWithEgg{
    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个香肠";
    }

    @Override
    public int getPrice() {
        return super.getPrice()+1;
    }
}
