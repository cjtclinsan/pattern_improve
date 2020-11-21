package jvm.clazz;

import java.io.*;

/**
 * @author woshi
 * @date 2020/11/19
 */
public class ClassReloading_2 {
    /**
     * 定义自己新的 classloader，从 classloader 继承
     */
    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            // 首先去找你要 load 的 class 文件，如果没找到去找父亲 load，如果找到了就自己 load
            // 我们把是不是已经加载过这部分逻辑省略，如果加载同一个 class 是覆盖不了的，但是把 ClassLoader 直接整体干掉就行
            File file = new File("F:/Work/pattern_improve/target/classes/" +
                    name.replace(".", "/").concat(".class"));

            if( !file.exists() ){
                return super.loadClass(name);
            }

            try {
                InputStream is = new FileInputStream(file);
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }
    }

    /**
     * 所以 tomcat 这么干，直接把 webapplication 的整个 classloader 全部干掉，重新加载一遍
     */
    public static void main(String[] args) throws ClassNotFoundException {
        MyLoader m = new MyLoader();
        Class clazz = m.loadClass("jvm.clazz.Hello");

        m = new MyLoader();
        Class clazzNew = m.loadClass("jvm.clazz.Hello");

        System.out.println(clazz == clazzNew);
    }
}