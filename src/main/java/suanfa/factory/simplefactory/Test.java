package suanfa.factory.simplefactory;

/**
 * @author taosh
 * @create 2020-03-03 21:50
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.create(JavaCourse.class);

        course.record();
    }
}
