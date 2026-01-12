class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int res = nums[0];
        int currMin = nums[0];
        int currMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = currMax;
                currMax = currMin;
                currMin = tmp;
            }

            currMax = Math.max(nums[i], nums[i] * currMax);
            currMin = Math.min(nums[i], nums[i] * currMin);
            res = Math.max(res, currMax);
            // int prevMin = currMin;
            // currMin = Math.min(nums[i], Math.min(nums[i] * currMin, nums[i] * currMax));
            // currMax = Math.max(nums[i], Math.max(nums[i] * prevMin, nums[i] * currMax));
            // res = Math.max(res, currMax);
        }

        return res;
    }
}