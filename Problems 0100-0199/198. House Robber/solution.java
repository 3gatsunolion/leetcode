class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int robTwoBefore = 0;
        int robOneBefore = nums[0];
        for (int i = 1; i < n; i++) {
            int tmp = robOneBefore;
            robOneBefore = Math.max(nums[i] + robTwoBefore, robOneBefore);
            robTwoBefore = tmp;
        }
        return robOneBefore;
    }
}