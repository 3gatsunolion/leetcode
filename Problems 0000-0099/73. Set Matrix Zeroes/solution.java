class Solution {
    public void setZeroes(int[][] matrix) {
        // Row 0 -> shows which columns should be zero
        // Col 0 -> shows which rows should be zero
        // Overlap: (0, 0) -> handle separately

        int m = matrix.length;
        int n = matrix[0].length;

        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    continue;
                }
                // Row i should be 0
                if (i == 0) {
                    isFirstRowZero = true;
                }
                if (j == 0) {
                    isFirstColZero = true;
                }
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (isFirstRowZero) {
            for (int col = 0; col < n; col++) {
                matrix[0][col] = 0;
            }
        }

        if (isFirstColZero) {
            for (int row = 0; row < m; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}