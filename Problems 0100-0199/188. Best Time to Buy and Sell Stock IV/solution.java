class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        // Can make max profit
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int maxHoldSoFar = -prices[0] + dp[0][i-1];
            for (int day = 1; day < n; day++) {
                dp[i][day] = Math.max(dp[i][day - 1], prices[day] + maxHoldSoFar);
                // dp[i - 1][day - 1] and dp[i - 1][day] is same, because if
                // the best case is to sell on day day, we can just combine
                // that with this buy and sell, so it will not take more 
                // than i transactions
                // maxHoldSoFar = Math.max(maxHoldSoFar, -prices[day] + dp[i - 1][day - 1]);
                maxHoldSoFar = Math.max(maxHoldSoFar, -prices[day] + dp[i - 1][day]);
            }
        }
        return dp[k][n - 1];

        // int[][] dp = new int[n][k + 1];
        // for (int sellDay = 1; sellDay < n; sellDay++) {
        //     for (int i = 1; i <= k; i++) {
        //         // Do nothing
        //         dp[sellDay][i] = dp[sellDay-1][i];
        //         // Sell today
        //         for (int buyDay = 0; buyDay < sellDay; buyDay++) {
        //             dp[sellDay][i] = Math.max(dp[sellDay][i], prices[sellDay] - prices[buyDay] + dp[buyDay][i - 1]);
        //         }
        //     }
        // }

        // return dp[n - 1][k];
    }
}