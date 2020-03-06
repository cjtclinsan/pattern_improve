package pattern_improve.singleton.seriable;

import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @author taosh
 * @create 2020-03-04 18:12
 */
public class SerializaSingleton implements Serializable {
    private SerializaSingleton(){}

    //序列化就是把内存中的状态通过转换成字节码的形式
    // 从而转换一个 I/O 流，写入其他地方（可以是磁盘、网络 I/O）
    // 内存中的状态会永久保存下来

    // 反序列化就是将已经持久化的字节码内容转换为 I/O 流
    // 通过 I/O 流的读取，进而将读取的内容转换为 Java 对象
    // 在转换过程中会重新创建对象 new

    private final static SerializaSingleton serializaSingleton = new SerializaSingleton();

    public static SerializaSingleton getInstance(){
        return serializaSingleton;
    }

    private Object readResolve(){
        return serializaSingleton;
    }
}
