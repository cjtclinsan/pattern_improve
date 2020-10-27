package threadpool;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author woshi
 * @date 2020/10/27
 */
public class CompletableFuture_1 {
    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();

//        priceOfTM();
//        priceOfJD();
//        priceOfTB();
//
//        end = System.currentTimeMillis();
//        System.out.println("use completable future !" + (end - start));

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());

        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

        CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str-> "price "+str)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("use completable future !" + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfJD() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 1.20;
    }

    private static double priceOfTM() {
        delay();
        return 1.30;
    }

    private static void delay() {
        int time = new Random().nextInt(500);

        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}