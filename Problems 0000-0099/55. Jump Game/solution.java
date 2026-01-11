class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int canStart = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] + i >= canStart) {
                canStart = i;
            }
        }
        return canStart == 0;
        // int n = nums.length;
        // int furthestSoFar = 0;
        // for (int i = 0; i < n - 1; i++) {
        //     if (furthestSoFar < i) {
        //         return false;
        //     }
        //     furthestSoFar = Math.max(furthestSoFar, i + nums[i]);
        // }
        // return furthestSoFar >= n - 1;
    }
}