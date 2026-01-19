class Solution {
    // Time complexity: O(m*n + min(m, n))
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        // sums[i + 1][j + 1] = sum of rectangle from (0, 0) to bottom right
        // corner (i, j)
        int[][] sums = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums[i + 1][j + 1] = mat[i][j] + sums[i][j + 1] + sums[i + 1][j] - sums[i][j];
            }
        }

        int maxLen = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                while (r + maxLen + 1 <= m && c + maxLen + 1 <= n) {
                    int sum = sums[r + maxLen + 1][c + maxLen + 1] - sums[r][c + maxLen + 1] - sums[r + maxLen + 1][c] + sums[r][c];
                    // Note: This works, because all numbers in mat
                    // are positive, so we can exit prematurely since
                    // sum will only increase
                    if (sum > threshold) break;
                    maxLen++;
                }
            }
        }

        return maxLen;
    }

    // // Time complexity: O(m*n*log(min(m, n)))
    // public int maxSideLength(int[][] mat, int threshold) {
    //     int m = mat.length;
    //     int n = mat[0].length;

    //     // sums[i + 1][j + 1] = sum of rectangle from (0, 0) to bottom right
    //     // corner (i, j)
    //     int[][] sums = new int[m + 1][n + 1];

    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             sums[i + 1][j + 1] = mat[i][j] + sums[i][j + 1] + sums[i + 1][j] - sums[i][j];
    //         }
    //     }

    //     int lo = 0;
    //     int hi = Math.min(m, n);

    //     while (lo < hi) {
    //         int mid = lo + (hi - lo + 1) / 2;
    //         if (exists(sums, mid, threshold)) {
    //             lo = mid;
    //         } else {
    //             hi = mid - 1;
    //         }
    //     }

    //     return lo;
    // }

    private boolean exists(int[][] sums, int sideLen, int threshold) {
        int m = sums.length;
        int n = sums[0].length;

        for (int i = sideLen; i < m; i++) {
            for (int j = sideLen; j < n; j++) {
                int sum = sums[i][j] - sums[i - sideLen][j] - sums[i][j - sideLen] + sums[i - sideLen][j - sideLen];
                if (sum <= threshold) return true;
            }
        }

        return false;
    }
}