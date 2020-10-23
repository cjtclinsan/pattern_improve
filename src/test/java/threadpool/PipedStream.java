package threadpool;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author woshi
 * @date 2020/10/23
 */
public class PipedStream {
    public static void main(String[] args) throws IOException {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "abcdefg".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";

        new Thread(()->{
            byte[] buffer = new byte[9];

            try {
                for (char c : c1) {
                    input1.read(buffer);

                    if( new String(buffer).equals(msg)){
                        System.out.println(c);
                    }
                    output1.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()->{
            byte[] buffer = new byte[9];
            try {
                for (char c : c2) {
                    System.out.println(c);
                    output2.write(msg.getBytes());
                    input2.read(buffer);
                    if( new String(buffer).equals(msg) ){
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}