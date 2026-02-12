class Solution {
    public int countNegatives(int[][] grid) {
        // int m = grid.length, n = grid[0].length;

        // int count = 0;
        // int colEnd = n - 1;

        // for (int r = 0; r < m; r++) {
        //     for (int c = 0; c <= colEnd; c++) {
        //         if (grid[r][c] < 0) {
        //             count += (colEnd - c + 1) * (m - r);
        //             colEnd = c - 1;
        //             break;
        //         }
        //     }

        //     if (colEnd < 0) break;
        // }

        // return count;

        // int m = grid.length, n = grid[0].length;
        // int count = 0;
        // for (int r = 0; r < m; r++) {
        //     int left = 0, right = n;

        //     while (left < right) {
        //         int mid = left + (right - left) / 2;
        //         if (grid[r][mid] < 0) {
        //             right = mid;
        //         } else {
        //             left = mid + 1;
        //         }
        //     }

        //     if (left < n) {
        //         count += n - left;
        //     }
        // }

        // return count;

        int m = grid.length, n = grid[0].length;
        // Start top right
        int r = 0, c = n - 1;
        int count = 0;
        while (r < m && c >= 0) {
            if (grid[r][c] >= 0) {
                r++;
            } else {
                count += m - r;
                c--;
            }
        }

        return count;
        // Time complexity: O(m + n)
    }
}