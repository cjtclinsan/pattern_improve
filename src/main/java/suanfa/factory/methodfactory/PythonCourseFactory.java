package suanfa.factory.methodfactory;

import suanfa.factory.simplefactory.ICourse;
import suanfa.factory.simplefactory.PythonCourse;

/**
 * @author taosh
 * @create 2020-03-03 22:05
 */
public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
