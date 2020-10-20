package reference;

import java.io.IOException;

/**
 * @author woshi
 * @date 2020/10/15
 */
public class NormalReference_1 {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.in.read();
    }
}