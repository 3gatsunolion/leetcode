class Solution {
    private final long MOD = (long) Math.pow(10, 9) + 7;
    public int concatenatedBinary(int n) {
        long res = 0;
        int numBits = 0;
        for (int num = 1; num <= n; num++) {
            if (num == (1 << numBits)) {
                numBits++;
            }
            // if ((num & (num - 1)) == 0) {
            //     numBits++;
            // }

            res = ((res << numBits) | num) % MOD;
        }

        return (int) res;
    }
}