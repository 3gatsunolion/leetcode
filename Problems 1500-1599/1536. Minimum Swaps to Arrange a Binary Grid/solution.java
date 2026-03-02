class Solution {
    public int minSwaps(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] trailingZeros = new int[m];

        for (int row = 0; row < m; row++) {
            int zeros = n;
            for (int col = n - 1; col >= 0; col--) {
                if (grid[row][col] == 1) {
                    zeros = n - col - 1;
                    break;
                }
            }
            trailingZeros[row] = zeros;
        }

        int res = 0;
        for (int row = 0; row < m - 1; row++) {
            int minTrailing = n - 1 - row;
            // Find first row that has trailing zeros >= minTrailing
            // Greedy approach works if we work from first row and
            // work our way down, because as we work our way down,
            // minTrailing decreases, so if swaps are needed, we are
            // pushing less minTrailing rows down which is ok
            int rowToSwap = -1;
            for (int r = row; r < m; r++) {
                if (trailingZeros[r] >= minTrailing) {
                    rowToSwap = r;
                    break;
                }
            }

            if (rowToSwap == -1) return -1;

            // Perform swaps
            for (int r = rowToSwap; r > row; r--) {
                trailingZeros[r] = trailingZeros[r - 1];
            }

            res += rowToSwap - row;
        }

        return res;
    }
}