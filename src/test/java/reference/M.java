package reference;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class M {
    @Override
    protected void finalize(){
        System.out.println("finalize");
    }
}