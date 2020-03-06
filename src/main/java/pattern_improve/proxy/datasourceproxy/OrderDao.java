package pattern_improve.proxy.datasourceproxy;

/**
 * @author taosh
 * @create 2020-03-05 17:04
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("orderDao创建Order成功");
        return 1;
    }
}
