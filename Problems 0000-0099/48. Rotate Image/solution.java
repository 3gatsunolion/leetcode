class Solution {
    public void rotate(int[][] matrix) {
        reverse(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    private void reverse(int[][] array) {
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int[] tmp = array[l];
            array[l] = array[r];
            array[r] = tmp;
            l++;
            r--;
        }
    }

    // public void rotate(int[][] matrix) {
    //     int n = matrix.length;
    //     int l = 0;
    //     int r = n - 1;

    //     while (l < r) {
    //         for (int i = 0; i < r - l; i++) {
    //             int top = l;
    //             int bottom = r;

    //             int tl = matrix[top][top + i];

    //             // bl -> tl
    //             matrix[top][top + i] = matrix[bottom - i][top];

    //             // br -> bl
    //             matrix[bottom - i][top] = matrix[bottom][bottom - i];

    //             // tr -> br
    //             matrix[bottom][bottom - i] = matrix[top + i][bottom];

    //             // tl -> tr
    //             matrix[top + i][bottom] = tl;
    //         }
    //         l++;
    //         r--;
    //     }
    // }
}