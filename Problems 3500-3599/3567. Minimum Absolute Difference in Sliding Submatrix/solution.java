class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int[][] ans = new int[m - k + 1][n - k + 1];
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                int[] kgrid = new int[k * k];
                int index = 0;
                for (int r = i; r < i + k; r++) {
                    for (int c = j; c < j + k; c++) {
                        kgrid[index++] = grid[r][c];
                    }
                }

                Arrays.sort(kgrid);
                int minDiff = Integer.MAX_VALUE;
                for (int x = 1; x < kgrid.length; x++) {
                    if (kgrid[x] != kgrid[x - 1]) {
                        int diff = kgrid[x] - kgrid[x - 1];
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }

                ans[i][j] = minDiff == Integer.MAX_VALUE ? 0 : minDiff;
            }
        }

        return ans;
    }
}