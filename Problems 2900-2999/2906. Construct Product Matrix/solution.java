class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int size = m * n;
        int MOD = 12345;

        long[] prefix = new long[size];
        
        // Build prefix
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            int r = (i - 1) / n;
            int c = (i - 1) % n;
            prefix[i] = (prefix[i - 1] * grid[r][c]) % MOD;
        }

        int[][] res = new int[m][n];

        // Build suffix + result
        long suffix = 1;
        for (int i = size - 1; i >= 0; i--) {
            int r = i / n;
            int c = i % n;

            res[r][c] = (int)((prefix[i] * suffix) % MOD);

            suffix = (suffix * grid[r][c]) % MOD;
        }

        return res;
    }
}