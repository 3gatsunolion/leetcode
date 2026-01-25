class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int coin : coins) {
            for (int amt = coin; amt <= amount; amt++) {
                dp[amt] = Math.min(dp[amt], 1 + dp[amt - coin]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
        // int[][] dp = new int[coins.length + 1][amount + 1];
        
        // for (int[] row : dp) {
        //     Arrays.fill(row, amount + 1);
        // }

        // // Zero coins to make 0 amount with no coins
        // dp[0][0] = 0;
        // for (int i = 1; i <= coins.length; i++) {
        //     // No coins needed to make 0 amount
        //     dp[i][0] = 0;
        //     for (int amt = 1; amt <= amount; amt++) {
        //         // Don't use this coin
        //         dp[i][amt] = dp[i-1][amt];
        //         if (coins[i-1] > amt) {
        //             continue;
        //         }
        //         dp[i][amt] = Math.min(dp[i][amt], 1 + dp[i][amt-coins[i-1]]);
        //     }
        // }

        // return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }
}