package suanfa.decorator.demo1;

/**
 * @author taosh
 * @create 2020-03-04 21:40
 */
public class BatterCakeWithEgg extends BatterCake{
    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice()+2;
    }
}
