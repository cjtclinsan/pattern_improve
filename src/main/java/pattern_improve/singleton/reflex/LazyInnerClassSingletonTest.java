package pattern_improve.singleton.reflex;

import pattern_improve.singleton.LazyInnerSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author taosh
 * @create 2020-03-04 17:22
 */
public class LazyInnerClassSingletonTest {
    public static void main(String[] args) {

        try {
            Class<?> clazz = LazyInnerSingleton.class;

            //通过反射获取私有方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问
            c.setAccessible(true);
            //初始化
            Object o1 = c.newInstance();

            //调用了两次构造方法，相当于new两次
            Object o2 = c.newInstance();

            System.out.println(o1 == o2);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
