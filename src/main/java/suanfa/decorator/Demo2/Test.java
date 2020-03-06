package suanfa.decorator.Demo2;

/**
 * @author taosh
 * @create 2020-03-04 21:47
 */
public class Test {
    public static void main(String[] args) {
        BatterCake batterCake;

        batterCake = new BaseBatterCake();

        System.out.println(batterCake.getMsg()+",价格"+batterCake.getPrice());

        batterCake = new EggBatterCake(batterCake);

        System.out.println(batterCake.getMsg()+",价格"+batterCake.getPrice());

        batterCake = new SauageBatterCake(batterCake);

        System.out.println(batterCake.getMsg()+",价格"+batterCake.getPrice());
    }
}
