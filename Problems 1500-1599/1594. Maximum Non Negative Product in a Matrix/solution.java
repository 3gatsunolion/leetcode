class Solution {

    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long MOD = 1_000_000_007;

        long[] min = new long[n];
        long[] max = new long[n];

        min[0] = max[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            min[i] = max[i] = min[i - 1] * grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            min[0] = max[0] = min[0] * grid[i][0];
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];
                if (val == 0) {
                    min[j] = max[j] = 0;
                } else if (val > 0) {
                    min[j] = Math.min(min[j], min[j - 1]) * val;
                    max[j] = Math.max(max[j], max[j - 1]) * val;
                } else {
                    long tmp = min[j];
                    min[j] = Math.max(max[j], max[j - 1]) * val;
                    max[j] = Math.min(tmp, min[j - 1]) * val;
                }
            }
        }

        return max[n - 1] < 0 ? -1 : (int) (max[n - 1] % MOD);
    }
}