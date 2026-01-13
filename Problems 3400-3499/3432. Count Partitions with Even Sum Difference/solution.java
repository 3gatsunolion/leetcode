class Solution {
    public int countPartitions(int[] nums) {
        // 1. odd - odd: 2a + 1 - (2b + 1) = 2a - 2b -> even
        // 2. odd - even: 2a + 1 - 2b = 2a - 2b + 1 -> odd
        // 3. even - even: even
        // Total sum MUST be even

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 == 1) return 0;

        int n = nums.length;
        return n - 1;
    }
}