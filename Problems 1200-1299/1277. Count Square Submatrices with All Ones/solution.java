class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    curr[j + 1] = 1 + Math.min(prev[j + 1], Math.min(prev[j], curr[j]));
                    res += curr[j + 1];
                } else {
                    curr[j + 1] = 0;
                }
            }
            // Switch prev and curr -> can't just set prev = curr,
            // since prev and curr will point to the same array
            // and curr will edit the same array in next iterations
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
        }
        return res;
        // int m = matrix.length;
        // int n = matrix[0].length;
        // int[][] dp = new int[m + 1][n + 1];
        // int res = 0;
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (matrix[i][j] == 1) {
        //             dp[i + 1][j + 1] = 1 + Math.min(dp[i + 1][j], Math.min(dp[i][j + 1], dp[i][j]));
        //             res += dp[i + 1][j + 1];
        //         }
        //     }
        // }
        // return res;
    }
}