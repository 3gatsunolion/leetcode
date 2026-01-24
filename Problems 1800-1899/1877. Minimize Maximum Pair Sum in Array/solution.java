class Solution {
    public int minPairSum(int[] nums) {
        // Minimizing the max sum always comes from pairing
        // max number with min.
        // Let's consider sorted array [a, b, c, d]
        // Proof by contradiction, let's assume there's more optimal
        // pairings that's NOT pairing off max and mins
        // So we have either:
        // 1. (a, b) (c, d)
        // or
        // 2. (a, c) (b, d)
        // Now let's compare the max sum pairs for each:
        // 1. max(a+b, c+d) = c + d
        // -> since c > a and c >= b and c, so c + d > BOTH a + d and b + c
        // 2. max(a+c, b+d)
        // -> b+d is > than BOTH a+d and b+c
        int n = nums.length;
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < n / 2; i++) {
            int sum = nums[i] + nums[n - i - 1];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
}