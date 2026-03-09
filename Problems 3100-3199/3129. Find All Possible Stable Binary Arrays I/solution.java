class Solution {
    int MOD = 1_000_000_007;
    public int numberOfStableArrays(int zero, int one, int limit) {
        // Basically can't have continuous string of more than limit
        // same character

        // Number of stable arrays ending in 0
        int[][] dp0 = new int[zero + 1][one + 1];
        // Number of stable arrays ending in 1
        int[][] dp1 = new int[zero + 1][one + 1];

        // Base cases
        for (int z = 1; z <= Math.min(zero, limit); z++) {
            dp0[z][0] = 1;
        }
        for (int o = 1; o <= Math.min(one, limit); o++) {
            dp1[0][o] = 1;
        }

        for (int z = 1; z <= zero; z++) {
            for (int o = 1; o <= one; o++) {
                dp0[z][o] = (dp0[z - 1][o] + dp1[z - 1][o]) % MOD;
                // We added dp0[z - 1][o] -> meaning there's cases of
                // arrays that end in limit + 1 zeros, which we need to
                // subtract. Arrays that end in limit + 1 zeros is given by
                // dp1[z - limit - 1][o]
                if (z > limit) {
                    dp0[z][o] = (dp0[z][o] - dp1[z - limit - 1][o] + MOD) % MOD;
                }

                dp1[z][o] = (dp0[z][o - 1] + dp1[z][o - 1]) % MOD;
                if (o > limit) {
                    dp1[z][o] = (dp1[z][o] - dp0[z][o - limit - 1] + MOD) % MOD;
                }
            }
        }

        return (dp0[zero][one] + dp1[zero][one]) % MOD;
    }
}