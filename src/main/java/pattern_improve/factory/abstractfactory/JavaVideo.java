package pattern_improve.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:12
 */
public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制java视频");
    }
}
