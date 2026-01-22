class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n < 2 || k == 0) return 0;

        // dp[i] = Highest profit with atmost k transactions from days 0 to i
        long[] dpCurr = new long[n];
        long[] dpPrev = new long[n];
        for (int t = 1; t <= k; t++) {
            // Highest profit after buying stock sometime from day 0 to i
            // with atmost t transactions
            long bestBuy = -prices[0];
            // Highest profit after short sell sometime from day 0 to i
            // with atmost t transactions
            long bestShort = prices[0];

            dpCurr[0] = 0;

            for (int day = 1; day < n; day++) {
                // Don't do anything
                long doNothing = dpCurr[day - 1];
                // Sell stock we bought before today
                long sell = bestBuy + prices[day];
                // Short sell
                long shortSell = bestShort - prices[day];

                dpCurr[day] = Math.max(Math.max(doNothing, sell), shortSell);

                bestBuy = Math.max(bestBuy, dpPrev[day - 1] - prices[day]);
                bestShort = Math.max(bestShort, dpPrev[day - 1] + prices[day]);
            }

            // Make sure to swap!! Because then both variables would point to
            // the same array, and on the next iteration you’d overwrite
            // values you still need
            long[] tmp = dpPrev;
            dpPrev = dpCurr;
            dpCurr = tmp;
        }

        return dpPrev[n - 1];
    }

    // public long maximumProfit(int[] prices, int k) {
    //     int n = prices.length;
    //     if (n < 2 || k == 0) return 0;

    //     long[] res = new long[k + 1];
    //     // bought[i] = Max profit where you bought a stock sometime
    //     // between day 0 and i, with at most k transactions
    //     long[] bought = new long[k + 1];
    //     long[] sold = new long[k + 1];

    //     Arrays.fill(bought, Long.MIN_VALUE);

    //     for (int day = 0; day < n; day++) {
    //         for (int t = k; t >= 1; t--) {
    //             // // Do nothing (Max profit from day 0 to previous day with
    //             // // at most t transactions)
    //             // res[day - 1][t]
    //             // // Sell today 
    //             // bought[day - 1][t - 1] + prices[day]
    //             // // Short sell
    //             // sold[day - 1][t - 1] - prices[day]
    //             res[t] = Math.max(res[t], Math.max(bought[t - 1] + prices[day], sold[t - 1] - prices[day]));
    //             bought[t - 1] = Math.max(bought[t - 1], res[t - 1] - prices[day]);
    //             sold[t - 1] = Math.max(sold[t - 1], res[t - 1] + prices[day]);
    //         }
    //     }

    //     long ans = 0;
    //     for (long p : res) {
    //         ans = Math.max(ans, p);
    //     }
    //     return ans;
    // }
}