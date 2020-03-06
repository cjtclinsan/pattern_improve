package pattern_improve.proxy.demo3;

import pattern_improve.proxy.demo2.IPerson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author taosh
 * @create 2020-03-05 16:30
 */
public class JdkMeipo implements InvocationHandler {
    private IPerson target;

    public IPerson getInstance(IPerson target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return (IPerson) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("双方同意，开始XX");
    }

    private void before() {
        System.out.println("媒婆开始物色");
    }
}
