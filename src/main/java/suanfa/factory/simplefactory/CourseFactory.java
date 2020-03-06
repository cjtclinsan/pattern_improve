package suanfa.factory.simplefactory;

/**
 * @author taosh
 * @create 2020-03-03 21:51
 */
public class CourseFactory {
    public ICourse create(Class<? extends ICourse> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if( null != clazz ){
            return clazz.newInstance();
        }else {
            return null;
        }
    }
}
