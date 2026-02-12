class Solution {
    public int[] getNoZeroIntegers(int n) {
        // 1012 -> 1 1
        // 101  -> 21 91
        // 9    -> 221 791

        // 122 -> [1 1], [11, 11], [111, 11]
        int a = 0, b = 0;
        int mulp = 1;
        while (n > 0) {
            int d = n % 10;
            n /= 10;

            // Since n > 0, this means n >= 10 here
            if (d == 0) {
                n--; // carry
                a += 1 * mulp;
                b += 9 * mulp;
            } else if (d == 1 && n > 0) {
                // Note: if d == 1 && n == 0, then that's the last
                // iteration, and we'll be left with one number
                // getting 1 and the other gets "0", but this "0"
                // is a leading zero, which isn't actually part of the number
                n--;
                a += 2 * mulp;
                b += 9 * mulp;
            } else {
                a += 1 * mulp;
                b += (d - 1) * mulp;
            }
            mulp *= 10;
        }

        return new int[] { a, b };
    }

    // public int[] getNoZeroIntegers(int n) {
    //     for (int a = 1; a <= n / 2; a++) {
    //         int b = n - a;
    //         // if (!String.valueOf(a).contains("0") &&
    //         //     !String.valueOf(b).contains("0")) {
    //         //         return new int[] { a, b };
    //         //     }
    //         if (isNoZero(a) && isNoZero(b)) {
    //             return new int[] { a, b };
    //         }
    //     }
    //     return new int[] {};
    // }

    // private boolean isNoZero(int n) {
    //     while (n != 0) {
    //         int d = n % 10;

    //         if (d == 0) return false;

    //         n /= 10;
    //     }
    //     return true;
    // }
}