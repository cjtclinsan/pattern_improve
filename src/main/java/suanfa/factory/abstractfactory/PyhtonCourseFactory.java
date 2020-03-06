package suanfa.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:15
 */
public class PyhtonCourseFactory extends CourseFactory {
    @Override
    protected INote createNote() {
        super.init();
        return new PythonNote();
    }

    @Override
    protected IVideo createVideo() {
        super.init();
        return new PythonVideo();
    }
}
