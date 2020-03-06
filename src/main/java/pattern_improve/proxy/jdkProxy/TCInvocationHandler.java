package pattern_improve.proxy.jdkProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author taosh
 * @create 2020-03-05 18:48
 */
public interface TCInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
