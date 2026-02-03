class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // Each subarry must be at least 2 in size
        if (i == 0 || i >= n - 2) return false;

        int d = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }

        if (i == d || i == n - 1) return false;

        while (i + 1 < n && nums[i] < nums[i + 1]) i++;

        return i == n - 1;
    }
}