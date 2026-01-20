class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        // (ans[i] | (ans[i] + 1)) -> always one even one odd
        // 1. And we can easily see that OR of even and odd will always be odd
        // Therefore nums[i] must be odd or else it will not work
        // 2. ans[i] + 1 -> flips rightmost zero to one, and all bits
        // to the right of this newly set bit to 0
        // Since we're ORing, (ans[i] | (ans[i] + 1)) is ans[i] but with
        // right most zero bit flipped to 1
        // So to find ans[i] from nums[i] we find right most zero, and the
        // 1 bit to the right of this is the one that was flipped by ans[i] + 1
        int n = nums.size();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num % 2 == 0) {
                res[i] = -1;
            } else {
                int bit = 1;
                while ((num & bit) != 0) {
                    bit <<= 1;
                }
                bit >>= 1;
                res[i] = (num & ~(bit));
            }
        }

        return res;
    }
}