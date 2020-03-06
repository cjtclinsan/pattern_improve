package suanfa.proxy.datasourceproxy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author taosh
 * @create 2020-03-05 17:48
 */
public class Test {
    public static void main(String[] args) {

        try {
            Order order = new Order();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse("2017/01/01");
            order.setCreateTime(date.getTime());

            IOrderService orderService = (IOrderService) new OrderServiceDynamicPorxy().getInstance(new OrderService());

            orderService.createOrder(order);
        } catch ( Exception e ){

        }


    }
}
