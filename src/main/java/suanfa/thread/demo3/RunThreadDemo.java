package suanfa.thread.demo3;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * 10个运动员100米赛跑，用程序实现让他们同时起跑，统计他们到达的名次。
 * 赛跑的时间可以使用自己的逻辑模拟就好
 * @author taosh
 * @create 2020-03-04 14:50
 */
public class RunThreadDemo {
    public static double m = 100;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());
        LinkedList<Mark> list = new LinkedList();

        //计数器
        CountDownLatch latch = new CountDownLatch(10);
        for( int i = 1; i <= 10; i++ ){
            Mark mark = new Mark("选手"+i, i, null);
            //随机一个速度
            int random = (int)(Math.random()*10+1);
            Sporter sporter = new Sporter(random, mark, latch);

            try {
                Mark mark1 = executor.submit(sporter).get();
                list.add(mark1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            latch.await(10, TimeUnit.SECONDS);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //对mark进行排序
        list.sort(Comparator.comparing(Mark::getTime));
        for (Mark mark : list) {
            System.out.println(mark.toString());
        }
    }

    static class Sporter implements Callable<Mark> {
        private int speed;
        private Mark mark;
        private CountDownLatch latch;

        public Sporter(int speed, Mark mark, CountDownLatch latch) {
            this.speed = speed;
            this.mark = mark;
            this.latch = latch;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public Mark getMark() {
            return mark;
        }

        public void setMark(Mark mark) {
            this.mark = mark;
        }

        public CountDownLatch getLatch() {
            return latch;
        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public Mark call() throws Exception {
            System.out.println("速度:"+speed+"m/s");
            BigDecimal time = new BigDecimal(m/speed);
            double arr = time.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("用时:"+arr+"s");
            mark.setTime(arr);
            latch.countDown();
            return mark;
        }

        //
    }

    static class Mark{
        private String name;
        private int index;
        private Double time;

        public Mark(String name, int index, Double time) {
            this.name = name;
            this.index = index;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Mark{" +
                    "name='" + name + '\'' +
                    ", index=" + index +
                    ", time=" + time +
                    '}';
        }
    }
}
