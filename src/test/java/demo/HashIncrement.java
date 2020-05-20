package demo;

/**
 * @author taosh
 * @create 2020-04-21 16:55
 */
public class HashIncrement {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        int n = 5;
        int max = 2 << (n-1);
        for( int i = 0; i < max; i++ ){
            System.out.print(i*HASH_INCREMENT & (max-1));
            System.out.print("  ");
        }
    }
}
