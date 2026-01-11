class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m - 1;

        int left = 0;
        int right = n - 1;

        int total = m * n;

        List<Integer> res = new ArrayList<Integer>();
        while (res.size() < total) {
            for (int col = left; col <= right && res.size() < total; col++) {
                res.add(matrix[top][col]);
            }
            top++;

            for (int row = top; row <= bottom && res.size() < total; row++) {
                res.add(matrix[row][right]);
            }
            right--;

            for (int col = right; col >= left && res.size() < total; col--) {
                res.add(matrix[bottom][col]);
            }
            bottom--;

            for (int row = bottom; row >= top && res.size() < total; row--) {
                res.add(matrix[row][left]);
            }
            left++;
        }
        return res;
    }
}