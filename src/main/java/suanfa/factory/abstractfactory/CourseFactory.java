package suanfa.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:11
 */
public abstract class CourseFactory {
    public void init(){
        System.out.println("初始化");
    }

    protected abstract INote createNote();

    protected abstract IVideo createVideo();
}
