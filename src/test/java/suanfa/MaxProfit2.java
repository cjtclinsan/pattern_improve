package suanfa;

import java.util.stream.IntStream;

/**
 * @author taosh
 * @create 2020-03-09 9:26
 */
public class MaxProfit2 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,3,1,3};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {

        int fb = Integer.MIN_VALUE, fs = 0;
        int sb = Integer.MIN_VALUE, ss = 0;
        for (int price : prices) {
            //第一次买入
            fb = Math.max( fb, -price );
            //第一次卖出
            fs = Math.max( fs, fb + price);

            //第二次买入
            sb = Math.max( sb, fs - price );
            //第二次卖出
            ss = Math.max( ss, sb + price );
        }

        return ss;
    }
}
