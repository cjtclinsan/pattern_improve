package suanfa.factory.methodfactory;

import suanfa.factory.simplefactory.ICourse;

/**
 * @author taosh
 * @create 2020-03-03 22:06
 */
public class Test {

    public static void main(String[] args) {
        ICourseFactory factory = new PythonCourseFactory();
        ICourse course = factory.create();
        course.record();
    }

}
