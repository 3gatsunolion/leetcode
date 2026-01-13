class Solution {
    private int[] DIRECTIONS = { -1, -1, 1, 1, -1 };
    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Combine [2][4] to one
        int[][][] dp = new int[m][n][1 << 3];
        // int[][][][] dp = new int[m][n][2][4];

        // for (int[][][] row1 : dp) {
        //     for (int[][] row2 : row1) {
        //         for (int[] row3 : row2) {
        //             Arrays.fill(row3, -1);
        //         }
        //     }
        // }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Must start at 1
                if (grid[i][j] == 1) {
                    for (int step = 0; step < 4; step++) {
                        int x = i + DIRECTIONS[step];
                        int y = j + DIRECTIONS[step + 1];

                        int len = 1 + findLongest(grid, x, y, 2, 0, step, dp);
                        if (len > res) {
                            res = len;
                        }
                    }
                }
            }
        }
        return res;
    }

    private int findLongest(int[][] grid, int x, int y, int target, int  hasTurned, int direction, int[][][] dp) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || y < 0 || x == m || y == n || grid[x][y] != target) {
            return 0;
        }

        int mask = (direction << 1) | hasTurned;

        if (dp[x][y][mask] > 0) {
            return dp[x][y][mask];
        }

        // Don't turn
        int dx = x + DIRECTIONS[direction];
        int dy = y + DIRECTIONS[direction + 1];
        int res = 1 + findLongest(grid, dx, dy, 2 - target, hasTurned, direction, dp);
        // Can turn
        if (hasTurned == 0) {
            dx = x + DIRECTIONS[(direction + 1) % 4];
            dy = y + DIRECTIONS[(direction + 2) % 4];
            res = Math.max(res, 1 + findLongest(grid, dx, dy, 2 - target, 1, (direction + 1) % 4, dp));
        }
        dp[x][y][mask] = res;
        return res;
    }
}