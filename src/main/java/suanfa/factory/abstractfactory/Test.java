package suanfa.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:16
 */
public class Test {
    public static void main(String[] args) {
        JavaCourseFactory factory = new JavaCourseFactory();
        factory.createNote().edit();
        factory.createVideo().record();
    }
}
