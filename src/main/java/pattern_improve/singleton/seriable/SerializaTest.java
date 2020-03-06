package pattern_improve.singleton.seriable;

import java.io.*;

/**
 * @author taosh
 * @create 2020-03-04 18:14
 */
public class SerializaTest {
    public static void main(String[] args) {
        SerializaSingleton s1 = null;

        SerializaSingleton s2 = SerializaSingleton.getInstance();

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("SerializaSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();


            FileInputStream fis = new FileInputStream("SerializaSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (SerializaSingleton) ois.readObject();
            ois.close();

            //反序列化后的对象和手动new的不一致
            System.out.println(s1+"   "+s2);
            System.out.println(s1 == s2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
