package suanfa;

/**
 * @author taosh
 * @create 2020-03-09 8:55
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,5,4,7,8};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        if( prices.length == 0 ){
            return 0;
        }
        int min = prices[0];
        int tmp = 0;
        for (int i = 1; i < prices.length; i++) {
            if( prices[i] < min ){
                min = prices[i];
            }
            if( prices[i] - min > tmp ){
                tmp = prices[i] - min;
            }
//            min = Math.min( min, prices[i]);
//            tmp = Math.max( tmp, prices[i]-min);
//            for( int j = i+1; j < prices.length; j++ ){
//                if( prices[j] - prices[i] > tmp){
//                    tmp = prices[j] - prices[i];
//                }
//            }
        }

        return tmp;
    }
}
