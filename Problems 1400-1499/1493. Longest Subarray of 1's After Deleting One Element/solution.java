class Solution {
    public int longestSubarray(int[] nums) {
        int count = 0;
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                count++;
            }
            if (count > 1) {
                if (nums[left++] == 0) {
                    count--;
                }
            }
        }
        return right - left - 1;
    }
}