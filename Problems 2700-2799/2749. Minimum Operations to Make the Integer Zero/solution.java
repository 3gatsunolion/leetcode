class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        int k = 0;
        long target = num1;
        while (target >= 0) {
            long bitCount = Long.bitCount(target);

            if (bitCount <= k && k <= target) {
                return k;
            }

            target -= num2;
            k++;
        }

        return -1;

        // // num1 = num2 * k + (x = 2^i + 2^(i') + ..) -> k terms

        // for (int k = 0; k <= 60; k++) {
        //     long target = num1 - 1l * k * num2;
        //     long bitCount = Long.bitCount(target);

        //     // k means we need k 2^i terms (can be the same i)
        //     // bitCount <= k -> if bitCount > k, we don't have enough
        //     // 2^i's to use to subtract with
        //     // k <= target -> if k > target, then we have too many
        //     // 2^i's left, since at most we can use target 2^i terms
        //     // (2^0 * target = target)
        //     if (target >= 0 && bitCount <= k && k <= target) {
        //         return k;
        //     }
        // }
        // return -1;
    }
}