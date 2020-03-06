package pattern_improve.proxy.cglib;

/**
 * @author taosh
 * @create 2020-03-05 20:47
 */
public class Test {
    public static void main(String[] args) {
        try {
            Customer customer = (Customer) new CglibMeipo().getInstance(Customer.class);
            customer.findLove();
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
