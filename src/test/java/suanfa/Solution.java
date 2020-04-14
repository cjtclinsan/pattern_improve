package suanfa;

import java.util.Arrays;

/**
 * @author taosh
 * @create 2020-03-08 15:43
 */
public class Solution {
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        recursion(coins, amount, 0, coins.length - 1);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }
    static int minCount = Integer.MAX_VALUE;
    /**
     * 1、按金额从大到小，从多到少（排序，用余数一步到位）
     * 2、预判低于最优解，终止递归（可以返回最优解，不过提升有限，意义不大）
     * 3、能整除即可返回
     */
    static void recursion(int[] coins, int amount, int count, int index) {
        if (index < 0 || count + amount / coins[index] >= minCount) {
            System.out.println("index1:"+index);
            return;
        }
        if (amount % coins[index] == 0) {
            System.out.println("index1:"+index);
            minCount = Math.min(minCount, count + amount / coins[index]);
            return;
        }
        for (int i = amount / coins[index]; i >= 0; i--) {
            System.out.println("index3:"+index);
            recursion(coins, amount - i * coins[index], count + i, index - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3};
        System.out.println(coinChange(arr, 26));
    }
}
