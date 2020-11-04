package jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author woshi
 * @date 2020/11/3
 */
public class PS {
    static List<Integer> nums = new ArrayList<>();
    static {
        Random random = new Random();
        for (int i = 0; i < 50000; i++) {
            nums.add(1000000+random.nextInt(1000000));
        }
    }

    static void foreach(){
        nums.forEach(i -> isPrime(i));
    }

    private static boolean isPrime(Integer i) {
        for (int j = 2; j <= i/2; j++) {
            if( i % j == 0 ){
                return false;
            }
        }
        return true;
    }

    static void parallel(){
        nums.parallelStream().forEach(PS::isPrime);
    }

    public static void main(String[] args) {
        System.out.println(12%8);
        System.out.println(12&(8-1));
    }
}