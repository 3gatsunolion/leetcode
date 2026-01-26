class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return nums[start];
        }
        int robTwoBefore = 0;
        int robOneBefore = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int tmp = robOneBefore;
            robOneBefore = Math.max(robOneBefore, nums[i] + robTwoBefore);
            robTwoBefore = tmp;
        }
        return robOneBefore;
    }
}