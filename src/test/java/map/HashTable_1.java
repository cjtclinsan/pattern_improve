package map;

import algorithm.hashTable.HashTable;

import java.util.UUID;

/**
 * @author woshi
 * @date 2020/10/21
 */
public class HashTable_1 {
    static HashTable<UUID, UUID> m = new HashTable<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count/THREAD_COUNT;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for( int i = start; i < start + gap; i++ ){
                m.put(UUID.randomUUID(), UUID.randomUUID());
            }

        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for( int i = 0; i < threads.length; i++ ){

            threads[i] = new MyThread(i*(count/THREAD_COUNT));
        }

        for( Thread t : threads ){
            t.start();
        }

        for( Thread t : threads ){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
}