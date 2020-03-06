package suanfa.factory.abstractfactory;

/**
 * @author taosh
 * @create 2020-03-03 22:13
 */
public class JavaNote implements INote {
    @Override
    public void edit() {
        System.out.println("编写java笔记");
    }
}
