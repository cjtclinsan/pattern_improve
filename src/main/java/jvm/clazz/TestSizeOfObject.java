package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/23
 */
public class TestSizeOfObject {
    public static void main(String[] args) {
        System.out.println(ObjectSizeAgent.sizeof(new Object()));
        System.out.println(ObjectSizeAgent.sizeof(new int[]{}));
        System.out.println(ObjectSizeAgent.sizeof(new P()));
    }

    private static class P {

        int id;
        String name;
        int age;

        byte b1;
        byte b2;

        Object o;
        byte b3;
    }
}