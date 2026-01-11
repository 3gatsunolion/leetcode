class Solution {
    // public int maximalRectangle(char[][] matrix) {
    //     int m = matrix.length;
    //     int n = matrix[0].length;

    //     // Basically we iterate through each cell of the
    //     // matrix and we want to know how far can we expand
    //     // left and right with the height of continuous 1s at matrix[i][j]
    //     int[] height = new int[n];
    //     int[] left = new int[n];
    //     int[] right = new int[n];

    //     Arrays.fill(right, n);

    //     int res = 0;
    //     for (int i = 0; i < m; i++) {
    //         int lastZeroIndex = -1;
    //         for (int j = 0; j < n; j++) {
    //             if (matrix[i][j] == '1') {
    //                 height[j]++;
    //                 left[j] = Math.max(lastZeroIndex + 1, left[j]);
    //             } else {
    //                 height[j] = 0;
    //                 left[j] = 0;
    //                 lastZeroIndex = j;
    //             }
    //         }

    //         lastZeroIndex = n;
    //         for (int j = n - 1; j >= 0; j--) {
    //             if (matrix[i][j] == '1') {
    //                 right[j] = Math.min(lastZeroIndex, right[j]);
    //             } else {
    //                 right[j] = n;
    //                 lastZeroIndex = j;
    //             }
    //         }

    //         for (int j = 0; j < n; j++) {
    //             res = Math.max(res, (right[j] - left[j]) * height[j]);
    //         }
    //     }

    //     return res;
    // }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] height = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            res = Math.max(res, largestHistogram(height));
        }

        return res;
    }

    private int largestHistogram(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int i = -1;

        int res = 0;
        for (int r = 0; r < n; r++) {
            while (i >= 0 && heights[stack[i]] > heights[r]) {
                int h = heights[stack[i--]];
                int l = (i >= 0 ? stack[i] : -1);

                res = Math.max(res, (r - l - 1) * h);
            }
            stack[++i] = r;
        }

        while (i >= 0) {
            int h = heights[stack[i--]];
            int r = n;
            int l = (i >= 0 ? stack[i] : -1);
            res = Math.max(res, (r - l - 1) * h);
        }

        return res;
    }
}