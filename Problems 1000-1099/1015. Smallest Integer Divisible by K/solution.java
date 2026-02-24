class Solution {
    public int smallestRepunitDivByK(int k) {
        // 1. If k is even number, then if n is divisible by k
        // it must be even as well
        // 2. If k = 5, then any number n divisible by k must
        // end in 0 or 5, so this is not possible either
        // 3. If k != {1, 3, 7, 9}
        // 1 % k = 1
        // 11 % k = (10 + 1) % k
        // 111 % k = (100 + 10 + 1) % k = (100 % k + prev_mod) % k
        // = (110 + 1) % k = (prev_mod * 10 + 1) % k
        if (k % 2 == 0 || k == 5) return -1;

        boolean[] seen = new boolean[k];

        // Since max we can get k different remainders
        // before it cycles in same order because the mod
        // is build from previous mod
        int rem = 0;
        for (int length = 1; length <= k; length++) {
            rem = (rem * 10 + 1) % k;
            if (rem == 0) return length;
            if (seen[rem]) return -1;
            seen[rem] = true;
        }

        return -1;
    }
}