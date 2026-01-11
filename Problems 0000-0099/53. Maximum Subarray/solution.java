class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int dp = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            res = Math.max(res, dp);
        }
        return res;
        // int n = nums.length;
        // int[] dp = new int[n];
        // dp[0] = nums[0];
        // int res = nums[0];
        // for (int i = 1; i < n; i++) {
        //     dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        //     res = Math.max(res, dp[i]);
        // }
        // return res;
    }
}