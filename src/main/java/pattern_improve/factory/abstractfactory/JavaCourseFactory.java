package pattern_improve.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:14
 */
public class JavaCourseFactory extends  CourseFactory {
    @Override
    protected INote createNote() {
        super.init();
        return new JavaNote();
    }

    @Override
    protected IVideo createVideo() {
        super.init();
        return new JavaVideo();
    }
}
