package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class ClassLoaderProcedure {
    public static void main(String[] args) {
        /**
         * count 在下时输出为 2，当调用 T 先把 T Classload 内存进行 verification 校验，
         * 然后进行 Preparation 赋默认值，这时候 T 是 0，count = 0 继续进行 Resolution，
         * 再进行 Initializing 初始化赋初始值，先调用 new T, count++ 变成 1，然后进行 count 赋值 = 2
         *
         * count 在上时输出位 3，在初始化时count赋值为2，然后调用 new T，count++ 所以变成3
         */
        System.out.println(T.count);
    }
}

class T {
    public static T t = new T();
    public static int count = 2;

    private T() {
        count++;
    }
}