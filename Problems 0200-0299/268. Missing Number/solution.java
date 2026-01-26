class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        
        int xor = n;
        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }

        return xor;

        // O(n) Time complexity but also O(n) space complexity
        // int n = nums.length;
        // int[] exists = new int[n + 1];

        // for (int num : nums) {
        //     exists[num] = 1;
        // }

        // for (int num = 0; num < n; num++) {
        //     if (exists[num] == 0) {
        //         return num;
        //     }
        // }

        // return n;

        // // Sort O(nlogn)
        // int n = nums.length;
        // Arrays.sort(nums);
        // for (int i = 0; i < n; i++) {
        //     if (nums[i] != i) {
        //         return i;
        //     }
        // }
        // return n;

        // // Brute force O(n^2)
        // int n = nums.length;
        // for (int cand = 0; cand < n; cand++) {
        //     boolean exists = false;
        //     for (int num : nums) {
        //         if (num == cand) {
        //             exists = true;
        //             break;
        //         }
        //     }

        //     if (!exists) {
        //         return cand;
        //     }
        // }
        // return n;
    }
}