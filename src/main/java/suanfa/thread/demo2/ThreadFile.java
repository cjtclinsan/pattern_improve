package suanfa.thread.demo2;

import java.util.List;

/**
 * @author taosh
 * @create 2020-03-04 11:35
 */
public class ThreadFile extends Thread {
    private String out;
    private List<String> list;

    public ThreadFile(String out, List<String> list) {
        this.out = out;
        this.list = list;
    }

    @Override
    public void run() {
        list.add(out);
    }
}
