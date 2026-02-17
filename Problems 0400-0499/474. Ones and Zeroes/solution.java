class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // int len = strs.length;
        // int[][][] dp = new int[len + 1][m + 1][n + 1];

        // for (int i = 1; i <= len; i++) {
        //     int ones = countSetBits(strs[i - 1]);
        //     int zeroes = strs[i - 1].length() - ones;

        //     for (int j = 0; j <= m; j++) {
        //         for (int k = 0; k <= n; k++) {
        //             int exclude = dp[i - 1][j][k];
        //             int include = zeroes <= j && ones <= k ? (dp[i - 1][j - zeroes][k - ones] + 1) : 0;
        //             dp[i][j][k] = Math.max(exclude, include);
        //         }
        //     }
        // }

        // return dp[len][m][n];

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            int ones = countSetBits(strs[i]);
            int zeroes = strs[i].length() - ones;

            for (int j = m; j >= zeroes; j--) {
                for (int k = n; k >= ones; k--) {
                    int exclude = dp[j][k];
                    int include = dp[j - zeroes][k - ones] + 1;
                    dp[j][k] = Math.max(exclude, include);
                }
            }
        }

        return dp[m][n];
    }

    private int countSetBits(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}