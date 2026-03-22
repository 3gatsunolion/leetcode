class Solution {
    // public boolean findRotation(int[][] mat, int[][] target) {
    //     boolean[] equal = new boolean[4];
    //     for (int i = 0; i < 4; i++) {
    //         equal[i] = true;
    //     }

    //     int n = mat.length;
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (mat[i][j] != target[i][j]) equal[0] = false;
    //             if (mat[i][j] != target[j][n - i - 1]) equal[1] = false;
    //             if (mat[i][j] != target[n - i - 1][n - j - 1]) equal[2] = false;
    //             if (mat[i][j] != target[n - j - 1][i]) equal[3] = false;
    //         }
    //     }

    //     return equal[0] | equal[1] | equal[2] | equal[3];
    // }

    private void swap(int[][] mat, int row, int col) {
        int tmp = mat[row][col];
        mat[row][col] = mat[col][row];
        mat[col][row] = tmp;
    }

    private void reverse(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo < hi) {
            int tmp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = tmp;
            lo++;
            hi--;
        }
    }

    private void rotate(int[][] mat) {
        int n = mat.length;
        // 1. Transpose
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(mat, i, j);
            }
        }

        // 2. Reverse rows
        for (int r = 0; r < n; r++) {
            reverse(mat[r]);
        }
    }

    private boolean isEqual(int[][] m1, int[][] m2) {
        int n = m1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }

        return true;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < 4; i++) {
            if (isEqual(mat, target)) return true;
            rotate(mat);
        }

        return false;
    }
}