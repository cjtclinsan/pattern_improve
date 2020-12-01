package jvm.gc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/12/1
 */
public class FullGC_Problem {
    private static class CardInfo{
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthDate = new Date();

        public void m(){

        }
    }

    // 线程池 new 出来
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);
        // 业务处理
        for (;;){
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        System.out.println(taskList.size());
        taskList.forEach(info-> executor.scheduleWithFixedDelay(()-> info.m(), 2, 3, TimeUnit.SECONDS));
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }
        return taskList;
    }
}