package AQS;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class HelloVarHandle_1 {
//    int x = 8;
//    private static VarHandle handle;
//    static {
//        try {
//            handle = MethodHandles.lookup().findVarHandle(HelloVarHandle_1.class, "x", int.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        HelloVarHandle_1 t = new HelloVarHandle_1();
//        System.out.println(handle.get(t));
//
//        handle.set(t, 9);
//        System.out.println(t.x);
//
//        handle.compareAndSet(t, 9, 10);
//        System.out.println(t.x);
//
//        handle.getAndAdd(t, 10);
//        System.out.println(t.x);
//
//    }
}