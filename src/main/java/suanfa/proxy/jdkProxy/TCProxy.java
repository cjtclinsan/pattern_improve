package suanfa.proxy.jdkProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

/**
 * @author taosh
 * @create 2020-03-05 18:49
 */
public class TCProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(TCClassLoader classLoader, Class<?> [] interfaces, TCInvocationHandler handler){
        try {
            //动态生成源文件.java文件
            String src = generateSrc(interfaces);

            //java文件输出到磁盘
            String filePath = TCProxy.class.getResource("").getPath();
            File f = new File(filePath+"$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            //把编译好的.class文件加载到jvm
            Class proxyClass = classLoader.findClass("$proxy0");
            Constructor c = proxyClass.getConstructor(TCInvocationHandler.class);
            f.delete();

            //返回字节码重组以后的新代理对象
            return c.newInstance(handler);

        }catch ( Exception e ){

        }

        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        //拼接一个class文件
        return "";
    }
}
