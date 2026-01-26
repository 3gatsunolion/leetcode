class Solution {
    public boolean isPowerOfTwo(int n) {
        // if (n <= 0) {
        //     return false;
        // }
        // while (n % 2 == 0) {
        //     n >>= 1;
        // }
        // return n == 1;

        // n - 1 removes the last set bit, if there exists another bit set
        // then (n & n - 1) will not equal 0
        return n > 0 && (n & n - 1) == 0;

        // Think of it bitwise -> only one bit is set
        // int count = 0;
        // for (int i = 0; i < 32; i++) {
        //     if ((n & (1 << i)) != 0) {
        //         count++;
        //     }

        //     if (count > 1) {
        //         return false;
        //     }
        // }
        // return count == 1;
    }
}