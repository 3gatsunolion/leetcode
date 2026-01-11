class Solution {
    public int climbStairs(int n) {
        // if (n < 2) {
        //     return 1;
        // }

        // int[] dp = new int[n + 1];
        // dp[0] = 1;
        // dp[1] = 1;

        // for (int i = 2; i <= n; i++) {
        //     dp[i] = dp[i - 1] + dp[i - 2];
        // }
        // return dp[n];

        if (n < 2) {
            return 1;
        }

        int twoStepBefore = 1;
        int oneStepBefore = 1;

        for (int i = 2; i <= n; i++) {
            int tmp = oneStepBefore;
            oneStepBefore = oneStepBefore + twoStepBefore;
            twoStepBefore = tmp;
        }
        return oneStepBefore;
    }
}