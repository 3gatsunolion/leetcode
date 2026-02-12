class Solution {
    public int sumFourDivisors(int[] nums) {
        // int res = 0;
        // for (int num : nums) {
        //     int count = 0;
        //     int sum = 0;
        //     for (int d1 = 1; d1 * d1 <= num; d1++) {
        //         if (num % d1 == 0) {
        //             int d2 = num / d1;
        //             count++;
        //             sum += d1;

        //             if (d1 != d2) {
        //                 count++;
        //                 sum += d2;
        //             }

        //             if (count > 4) break;
        //         }
        //     }

        //     if (count != 4) {
        //         sum = 0;
        //     }

        //     res += sum;
        // }

        // return res;

        int res = 0;
        for (int num : nums) {
            // We know for a fact that every num has divisor 1 and num
            // unless num = 1, so we just need to find next pair divisor
            // and make sure it's the only other pair divisor
            int d = 0;
            for (int cand = 2; cand * cand <= num; cand++) {
                if (num % cand == 0) {
                    if (d == 0) {
                        d = cand;
                    } else {
                        // More than 4 divisors
                        d = 0;
                        break;
                    }
                }
            }

            if (d > 0 && d != (num / d)) {
                res += 1 + num + d + (num / d);
            }
        }

        return res;
    }
}