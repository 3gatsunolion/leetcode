class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        Integer[][] dp = new Integer[m][n];

        return dfs(0, 0, triangle, dp);
    }

    private int dfs(int row, int col, List<List<Integer>> triangle, Integer[][] dp) {
        int m = triangle.size();
        if (row == m) return 0;
        if (dp[row][col] != null) return dp[row][col];

        dp[row][col] = triangle.get(row).get(col) + Math.min(dfs(row + 1, col, triangle, dp), dfs(row + 1, col + 1, triangle, dp));

        return dp[row][col];
    }
    // public int minimumTotal(List<List<Integer>> triangle) {
    //     int m = triangle.size();
    //     int n = triangle.get(m - 1).size();
    //     int[] dp = new int[n + 1];

    //     for (int row = m - 1; row >= 0; row--) {
    //         List<Integer> curr = triangle.get(row);
    //         for (int col = 0; col <= row; col++) {
    //             dp[col] = curr.get(col) + Math.min(dp[col], dp[col + 1]);
    //         }
    //     }

    //     return dp[0];
    // }
}