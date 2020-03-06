package pattern_improve.factory.simplefactory;

/**
 * @author taosh
 * @create 2020-03-03 21:51
 */
public class PythonCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("录制python视频");
    }
}
