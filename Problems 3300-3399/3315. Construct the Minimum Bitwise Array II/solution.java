class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        // ans[i] | ans[i] + 1 -> will always result in odd number
        // when you add +1 to a number, you are basically finding first
        // zero, flipping that and setting everything to the right zero
        // So nums[i] will be ans[i] but with the first zero flipped
        // So if we want to derive ans[i] from nums[i], we can find
        // first zero bit and the 1 bit to the right of this will
        // be the bit that was flipped
        // Time complexity: O(n * log2(max(nums)))
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num % 2 == 0) {
                ans[i] = -1;
            } else {
                // int bit = 1;
                // while ((num & bit << 1) > 0) {
                //     bit <<= 1;
                // }
                // ans[i] = (num & ~bit);
                
                // num ^ (num + 1) = 0011111 -> all zeroes then all ones
                // from the right most zero bit in num
                // +1 so we get just one bit that is 1
                // >> 2 so the bit is positioned at the bit that was flipped
                // in ans[i]
                int bit = (num ^ (num + 1)) + 1 >> 2;
                ans[i] = num ^ bit;
            }
        }
        return ans;
    }
}