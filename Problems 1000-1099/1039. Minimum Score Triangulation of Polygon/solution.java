class Solution {
    public int minScoreTriangulation(int[] values) {
        // Since you must divide it so its made entirely of triangles
        // the triangles have to be touching each other
        // KEY: values is given in clockwise order
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int len = 3; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                dp[start][end] = Integer.MAX_VALUE;
                for (int k = start + 1; k < end; k++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k][end] + values[start] * values[end] * values[k]);
                }

            }
        }
        return dp[0][n - 1];
    }
}