class Solution {
    private final int MOD = (int) 1e9 + 7;
    public int numberOfWays(int n, int x) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int a = 1; (int) Math.pow(a, x) <= n; a++) {
            int pow = (int) Math.pow(a, x);
            for (int num = n; num >= pow; num--) {
                dp[num] = (dp[num] + dp[num - pow]) % MOD;
            }
        }
        return dp[n];
        // // a^x = n -> a = n ^ (1/x)
        // int a = (int) Math.ceil(Math.pow(n, 1.0 / x));
        // // dp[i][j] = how many unique ways to express i with sum of powers
        // // of range [1,j]
        // int[][] dp = new int[n + 1][a + 1];
        
        // // 1 way to express 0 with sum of powers
        // Arrays.fill(dp[0], 1);

        // for (int num = 1; num <= n; num++) {
        //     for (int i = 1; i <= a; i++) {
        //         dp[num][i] = dp[num][i - 1];
        //         int pow = (int) Math.pow(i, x);
        //         if (pow > num) continue;
        //         dp[num][i] = (dp[num][i] + dp[num - pow][i - 1]) % MOD;
        //     }
        // }
        // return dp[n][a];
    }
}