package jvm.clazz;

import java.io.*;

/**
 * @author woshi
 * @date 2020/11/18
 */
// 继承 ClassLoader
public class TcClassLoader extends ClassLoader {
    // 重写 findClass 方法，然后找到要 load 进来的二进制内容，load 完成之后再转换成对象
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("E://", name.replace(".", "/").concat(".class"));

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != 0){
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();

            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        ClassLoader loader = new TcClassLoader();
        Class clazz = loader.loadClass("jvm.clazz.Hello");
        Class clazz1 = loader.loadClass("jvm.clazz.Hello");

        System.out.println(clazz == clazz1);

        Hello h = (Hello) clazz.newInstance();
        h.m(1);

        // AppClassLoader
        System.out.println(loader.getClass().getClassLoader());

        // AppClassLoader
        System.out.println(loader.getParent());

        System.out.println(getSystemClassLoader());
    }
}