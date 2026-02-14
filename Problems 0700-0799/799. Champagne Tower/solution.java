class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // double[] prev = new double[query_row + 1];
        // prev[0] = poured;

        // for (int row = 0; row < query_row; row++) {
        //     double[] curr = new double[query_row + 1];
        //     for (int cup = 0; cup <= row; cup++) {
        //         double leftover = Math.max(0, prev[cup] - 1);
        //         double halved = leftover / 2;
        //         curr[cup] += halved;
        //         curr[cup + 1] += halved;
        //     }

        //     prev = curr;
        // }

        // return Math.min(1.0, prev[query_glass]);

        double[] dp = new double[query_row + 1];
        dp[0] = poured;

        for (int row = 0; row < query_row; row++) {
            for (int cup = row; cup >= 0; cup--) {
                if (dp[cup] > 1) {
                    double leftover = dp[cup] - 1;
                    double halved = leftover / 2;
                    dp[cup] = halved;
                    dp[cup + 1] += halved;
                } else {
                    dp[cup] = 0;
                }
            }
        }

        return Math.min(1.0, dp[query_glass]);
    }
}