package suanfa.decorator.demo1;

/**
 * @author taosh
 * @create 2020-03-06 11:18
 */
public class Tset {
    public static void main(String[] args) {
        BatterCake batterCake = new BatterCake();
        System.out.println(batterCake.getMsg()+",总价"+batterCake.getPrice());

        BatterCake batterCakeWithEgg = new BatterCakeWithEgg();
        System.out.println(batterCakeWithEgg.getMsg()+",总价"+batterCakeWithEgg.getPrice());

        BatterCake batterCakeWithEggAndSauage = new BatterCakeWithEggAndSauage();
        System.out.println(batterCakeWithEggAndSauage.getMsg()+",总价"+batterCakeWithEggAndSauage.getPrice());


    }
}
