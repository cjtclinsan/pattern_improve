package jucTool;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/6/2
 * 阶段:结合了CountDownLatch和CyclicBarrier
 * 是按照不同的阶段来对线程进行操作，本身维护着阶段这样一个成员变量（当前线程执行到哪个阶段，第0，第1...）
 * 每个阶段不同的时候，线程都可以往前走，有的线程走到某个阶段就停止了，有的线程可以一直到结束。
 * 如果程序中要用到好几个阶段，并且有的阶段必须几个人共同参与的情况可以使用Phaser
 * 不仅可以控制栅栏的个数，还可以控制栅栏的等待数量
 */
public class PhaserDemo {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();
    static void milliSleep(int milli){
        try {
            TimeUnit.SECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //7个人参加婚礼
        phaser.bulkRegister(7);

        for( int i = 0; i < 5; i++ ){
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties){
            switch (phase){
                case 0:
                    System.out.println("所有人到齐!"+registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完!"+registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开!"+registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束，新郎新娘进入洞房!"+registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable{
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive(){
            milliSleep(r.nextInt(10));
            System.out.printf("%s 到达现场!\n", name);
            //让线程在栅栏前停住，到达等待继续往前走   每一个阶段
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            milliSleep(r.nextInt(10));
            System.out.printf("%s 吃完!\n", name);
            //让线程在栅栏前停住，到达等待继续往前走   每一个阶段
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            milliSleep(r.nextInt(10));
            System.out.printf("%s 离开现场!\n", name);
            //让线程在栅栏前停住，到达等待继续往前走   每一个阶段
            phaser.arriveAndAwaitAdvance();
        }

        private void hug(){
            if( name.equals("新郎") || name.equals("新娘") ){
                milliSleep(r.nextInt(10));
                System.out.printf("%s 拥抱!\n", name);
                //让线程在栅栏前停住，到达等待继续往前走   每一个阶段
                phaser.arriveAndAwaitAdvance();
            }else {
                //其他人不参与
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}