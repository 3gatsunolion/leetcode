class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        // a ^ a = 0 -> so the two sneaky numbers
        // will be cancelled out here
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // Now xor is xor of the two sneaky numbers
        int n = nums.length - 2;
        for (int num = 0; num < n; num++) {
            xor ^= num;
        }

        // Since the two sneaky numbers are different, xor
        // is NOT 0, so it will have at least one set bit
        int rsb = xor & ~(xor - 1);

        // We can group numbers that have rsb set, and those
        // that do not, we know the two sneaky numbers will
        // be in separate groups
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & rsb) != 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        for (int num = 0; num < n; num++) {
            if ((num & rsb) != 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;

        // int n = nums.length - 2;
        // boolean[] seen = new boolean[n + 1];
        // int[] res = new int[2];
        // int i = 0;
        // for (int num : nums) {
        //     if (seen[num]) {
        //         res[i++] = num;
        //     }
        //     seen[num] = true;
        // }
        // return res;
    }
}