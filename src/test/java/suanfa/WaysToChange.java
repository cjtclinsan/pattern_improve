package suanfa;

/**
 * @author taosh
 * @create 2020-04-23 9:20
 */
public class WaysToChange {
    private static int MOD = (int) (Math.pow(10, 9)+7);
    //动态规划   [完全背包问题]
    public static int waysToChange(int n) {
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n+1];

        /**
         * dp[0] 初始化为 1，为了刚好选择 coin 能够凑成 amount，则其方法数为 1
         * 比如 amoutn = 1，然后选择了 coin = 1，那么就是剩下的 0 元，那么组成 1 元的方法数为 1
         */
        dp[0] = 1;
        for (int coin : coins) {
            for( int i = coin; i <= n; i++ ){
                dp[i] = (dp[i]+ dp[i-coin]) % MOD;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(waysToChange(929782));
    }
}
