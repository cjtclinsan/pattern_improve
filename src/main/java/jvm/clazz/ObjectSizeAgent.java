package jvm.clazz;

import java.lang.instrument.Instrumentation;

/**
 * @author woshi
 * @date 2020/11/23
 */
public class ObjectSizeAgent {
    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation _inst){
        System.out.println(123);
        inst = _inst;
    }

    public static long sizeof(Object o){
        System.out.println(inst);
        return inst.getObjectSize(o);
    }
}