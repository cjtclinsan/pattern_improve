package pattern_improve.prototype;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taosh
 * @create 2020-03-04 19:55
 */
@Data
public class ConcreatePrototype implements Cloneable, Serializable {
    private int age;
    private String name;
    private List<String> hobbies;

    @Override
    public ConcreatePrototype clone(){
        try {
            return (ConcreatePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用序列化来实现深度克隆
     * @return
     */
    public ConcreatePrototype deepClone(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ConcreatePrototype) ois.readObject();

        } catch ( Exception e ){
            e.printStackTrace();
            return null;
        }
    }

    public ConcreatePrototype deepCloneHobbies(){
        try {
            ConcreatePrototype result = (ConcreatePrototype) super.clone();
            result.hobbies = (List<String>) ((ArrayList)result.hobbies).clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" + "age=" + age + ", name='" + name + '\'' + ", hobbies=" + hobbies + '}';
    }
}
