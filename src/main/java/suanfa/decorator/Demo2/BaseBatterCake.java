package suanfa.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:43
 */
public class BaseBatterCake extends BatterCake {
    @Override
    protected int getPrice() {
        return 5;
    }

    @Override
    protected String getMsg() {
        return "煎饼";
    }
}
