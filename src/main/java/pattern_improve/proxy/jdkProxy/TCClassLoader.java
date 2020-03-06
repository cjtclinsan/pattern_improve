package pattern_improve.proxy.jdkProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author taosh
 * @create 2020-03-05 18:55
 */
public class TCClassLoader extends ClassLoader {
    private File classPathFile;

    public TCClassLoader() {
        String classPath = TCClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name){
        String className = TCClassLoader.class.getPackage().getName()+"."+name;
        if( classPathFile != null ){
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/")+".class");
            if( classFile.exists() ){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;

                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ( (len = in.read(buff)) != -1 ){
                        out.write(buff, 0, len);
                    }

                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch ( Exception e ){
                    e.printStackTrace();
                } finally {
                    if ( null != in ){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if( null != out ){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }
}
