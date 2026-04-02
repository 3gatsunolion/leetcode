class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        // dp[i][0] = no neutralize
        // dp[i][1] = at most 1 neutralize
        // dp[i][2] = at most 2 neutralize
        int[][] dp = new int[n + 1][3];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }

        for (int i = 0; i < 3; i++) {
            dp[1][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                int coin = coins[i][j-1];

                // Either take coins/get robbed or neutralize
                dp[j][2] = Math.max(
                    Math.max(dp[j-1][2], dp[j][2]) + coin,
                    Math.max(dp[j-1][1], dp[j][1])
                );
                
                dp[j][1] = Math.max(
                    Math.max(dp[j-1][1], dp[j][1]) + coin,
                    Math.max(dp[j-1][0], dp[j][0])
                );

                dp[j][0] = Math.max(dp[j-1][0], dp[j][0]) + coin;
            }
        }

        return dp[n][2];
    }
}