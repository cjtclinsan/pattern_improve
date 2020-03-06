package suanfa.proxy.jdkProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author taosh
 * @create 2020-03-05 19:58
 */
public class TCMeipo implements TCInvocationHandler {
    //被代理对象，把引用保存下来
    private Object target;

    public Object getInstance(Object object){
        this.target = object;
        Class<?> clazz = target.getClass();
        return TCProxy.newProxyInstance(new TCClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        before();
        method.invoke(this.target, args);
        after();
        return null;
    }

    private void after() {
        System.out.println("我是媒婆：我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

    private void before() {
        System.out.println("如果合适的话，就准备办事");
    }
}
