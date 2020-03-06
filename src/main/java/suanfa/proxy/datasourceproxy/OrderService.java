package suanfa.proxy.datasourceproxy;

/**
 * @author taosh
 * @create 2020-03-05 17:06
 */
public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService 调用orderDao创建订单");
        return orderDao.insert(order);
    }
}
