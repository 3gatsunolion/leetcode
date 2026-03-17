class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) continue;

                // Keep track of height of "histogram bar"
                // ending at matrix[i][j]
                matrix[i][j] += matrix[i - 1][j];
            }
        }
        
        int res = 0;
        for (int i = 0; i < m; i++) {
            // Since we can reorder columns, let's sort the
            // histogram bars at each row so that when we iterate
            // over the columns, at column j, we know that all column
            // heights to the right of j have >= height and we can
            // definitely form a rectangle with them
            Arrays.sort(matrix[i]);
            for (int j = 0; j < n; j++) {
                int width = n - j;
                res = Math.max(res, matrix[i][j] * width);
            }
        }

        return res;
    }
}