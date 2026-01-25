class Solution {
    public long maxMatrixSum(int[][] matrix) {
        // As long as there are even amount of negative numbers
        // you can turn them positive
        // So if there's odd number, just smallest "possible" negative
        // number
        // EXCEPTION: 0 x anything is 0, so if there is at least
        // one 0, then we can convert all negative numbers into positive
        long sum = 0;
        int largestNegative = Integer.MAX_VALUE;
        int countNeg = 0;
        boolean hasZero = false;
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == 0) hasZero = true;
                if (num < 0) {
                    countNeg++;
                    num = -num;
                }
                largestNegative = Math.min(num, largestNegative);
                sum += num;
            }
        }

        if (hasZero) return sum;

        return sum - (countNeg % 2 == 1 ? 2 * largestNegative : 0);
    }
}