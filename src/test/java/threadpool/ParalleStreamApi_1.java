package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author woshi
 * @date 2020/11/2
 */
public class ParalleStreamApi_1 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }

        long startTime = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);   // 1700ms

        startTime = System.currentTimeMillis();
        nums.parallelStream().forEach(ParalleStreamApi_1::isPrime);
        endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);  // 363ms
    }

    private static boolean isPrime(Integer v) {
        for (int i = 2; i <= v/2; i++) {
            if( v % i == 0 ){
                return false;
            }
        }
        return true;
    }
}