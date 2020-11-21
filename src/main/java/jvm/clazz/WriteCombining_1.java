package jvm.clazz;

import java.util.Random;
import java.util.WeakHashMap;

/**
 * @author woshi
 * @date 2020/11/20
 */
public class WriteCombining_1 {
    private static final int ITERATIONS = Integer.MAX_VALUE;
    private static final int ITEMS = 1 << 24;
    private static final int MASK = ITEMS - 1;

    private static final byte[] arrayA = new byte[ITEMS];
    private static final byte[] arrayB = new byte[ITEMS];
    private static final byte[] arrayC = new byte[ITEMS];
    private static final byte[] arrayD = new byte[ITEMS];
    private static final byte[] arrayE = new byte[ITEMS];
    private static final byte[] arrayF = new byte[ITEMS];

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "SingleLoop duration(ns) = "+runCaseOne());
            System.out.println(i + "SplitLoop duration(ns) = "+runCaseTwo());
        }
    }

    private static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while ( --i != 0){
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        return System.nanoTime() - start;
    }

    public static long runCaseTwo(){
        long start = System.nanoTime();
        int i = ITERATIONS;

        while ( --i != 0){
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
        }

        int j = ITERATIONS;
        while ( --j != 0){
            int slot = j & MASK;
            byte b = (byte) j;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        return System.nanoTime() - start;
    }
}