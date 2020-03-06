package pattern_improve.factory.methodfactory;

import pattern_improve.factory.simplefactory.ICourse;
import pattern_improve.factory.simplefactory.JavaCourse;

/**
 * @author taosh
 * @create 2020-03-03 22:05
 */
public class JavaCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
