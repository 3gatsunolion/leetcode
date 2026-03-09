class Solution {
    public int minimumSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 1.        2.      3.
        // ┌－┐      ┌┐┌┐    ┌－┐
        // └－┘      └┘└┘    └－┘
        // ┌－┐      ┌－┐    ┌┐┌┐
        // └－┘      └－┘    └┘└┘
        // ┌－┐
        // └－┘

        // 4.       5.      6.
        // ┌┐┌┐┌┐   ┌ ┐┌┐    ┌┐┌ ┐
        // └┘└┘└┘   │ │└┘    └┘│ │
        //          │ │┌┐    ┌┐│ │
        //          └ ┘└┘    └┘└ ┘

        int res = m * n;
        for (int i = 1; i < m; i++) {
            int a1 = minimumArea(grid, 0, 0, i - 1, n - 1);
            int a2 = minimumArea(grid, m - i, 0, m - 1, n - 1);
            for (int j = 1; j < n; j++) {
                // Type 3
                int a3 = minimumArea(grid, i, 0, m - 1, j - 1);
                int a4 = minimumArea(grid, i, j, m - 1, n - 1);
                res = Math.min(res, a1 + a3 + a4);

                // Type 2
                a3 = minimumArea(grid, 0, 0, m - i - 1, j - 1);
                a4 = minimumArea(grid, 0, j, m - i - 1, n - 1);
                res = Math.min(res, a2 + a3 + a4);
            }
            
            // Type 1
            for (int i2 = i + 1; i2 < m; i2++) {
                int a3 = minimumArea(grid, i, 0, i2 - 1, n - 1);
                int a4 = minimumArea(grid, i2, 0, m - 1, n - 1);
                res = Math.min(res, a1 + a3 + a4);
            }
        }

        for (int j = 1; j < n; j++) {
            int a1 = minimumArea(grid, 0, 0, m - 1, j - 1);
            int a2 = minimumArea(grid, 0, n - j, m - 1, n - 1);

            for (int i = 1; i < m; i++) {
                // Type 5
                int a3 = minimumArea(grid, 0, j, i - 1, n - 1);
                int a4 = minimumArea(grid, i, j, m - 1, n - 1);
                res = Math.min(res, a1 + a3 + a4);

                // Type 6
                a3 = minimumArea(grid, 0, 0, i - 1, n - j - 1);
                a4 = minimumArea(grid, i, 0, m - 1, n - j - 1);
                res = Math.min(res, a2 + a3 + a4);
            }

            for (int j2 = j + 1; j2 < n; j2++) {
                // Type 4
                int a3 = minimumArea(grid, 0, j, m - 1, j2 - 1);
                int a4 = minimumArea(grid, 0, j2, m - 1, n - 1);
                res = Math.min(res, a1 + a3 + a4);
            }
        }

        return res;
    }

    private int minimumArea(int[][] grid, int sr, int sc, int er, int ec) {
        int top = er;
        int bottom = sr;
        int left = ec;
        int right = sc;
        boolean found = false;
        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++) {
                if (grid[i][j] == 1) {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    found = true;
                }
            }
        }

        return found ? ((right - left + 1) * (bottom - top + 1)) : 0;
    }
}