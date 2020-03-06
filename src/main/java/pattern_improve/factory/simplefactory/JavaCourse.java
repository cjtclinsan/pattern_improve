package pattern_improve.factory.simplefactory;

/**
 * @author taosh
 * @create 2020-03-03 21:49
 */
public class JavaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("录制java视频");
    }
}
