class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSums = new int[m][n + 1];
        int[][] colSums = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSums[i][j + 1] = rowSums[i][j] + grid[i][j];
                colSums[i + 1][j] = colSums[i][j] + grid[i][j];
            }
        }

        for (int squareLen = Math.min(m, n); squareLen > 1; squareLen--) {
            if (magicSquareExists(grid, rowSums, colSums, squareLen)) {
                return squareLen;
            }
        }
        
        return 1;
    }

    private boolean magicSquareExists(
        int[][] grid,
        int[][] rowSums,
        int[][] colSums,
        int squareLen)
    {
        int m = grid.length;
        int n = grid[0].length;

        for (int row = 0; row <= m - squareLen; row++) {
            for (int col = 0; col <= n - squareLen; col++) {
                int targetSum = rowSums[row][col + squareLen] - rowSums[row][col];

                // Keep track of diagonal sums as you go
                int d1 = 0;
                int d2 = 0;
                boolean matches = true;
                for (int i = 0; i < squareLen; i++) {
                    int sum1 = rowSums[row + i][col + squareLen] - rowSums[row + i][col];
                    int sum2 = colSums[row + squareLen][col + i] - colSums[row][col + i];

                    d1 += grid[row + i][col + i];
                    d2 += grid[row + i][col + squareLen - 1 - i];
                    if (sum1 != sum2 || sum1 != targetSum) {
                        matches = false;
                        break;
                    }
                }

                if (matches && d1 == d2 && d1 == targetSum) {
                    return true;
                }
            }
        }

        return false;
    }
}