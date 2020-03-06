package pattern_improve.prototype;

import org.omg.PortableInterceptor.HOLDING;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taosh
 * @create 2020-03-04 19:57
 */
public class Test {
    public static void main(String[] args) {
        ConcreatePrototype prototype = new ConcreatePrototype();

        prototype.setAge(18);
        prototype.setName("tcc");
        List<String> hobbis = new ArrayList<>();
        hobbis.add("篮球");
        hobbis.add("游戏");
        prototype.setHobbies(hobbis);

        ConcreatePrototype cloneType= prototype.deepCloneHobbies();
        cloneType.getHobbies().add("编码");

        System.out.println(prototype.toString());
        System.out.println(cloneType.toString());
    }
}
