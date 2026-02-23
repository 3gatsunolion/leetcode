class Solution {
    private final long MOD = (long) Math.pow(10, 9) + 7;
    public int numOfWays(int n) {
        // Three different colors
        // Each row can only have one of two patterns: ABA, ABC
        // Pattern ABA: RGR, RYR, GRG, GYG, YRY, YGY
        // Pattern ABC: RGY, RYG, GRY, GYR, YRG, YGR
        // Row with RGR can be followed by:
        // GRG, GYG, YRY, GRY, YRG
        // Extend to any row with pattern ABA can be followed by
        // (3 ABA, 2 ABC)
        // Row with pattern ABC can be followed by:
        // (2 ABA, 2 ABC)

        if (n == 0) return 0;
        if (n == 1) return 12;

        long aba = 6;
        long abc = 6;

        for (int row = 2; row <= n; row++) {
            long tmp = (aba * 3 + abc * 2) % MOD;
            abc = (aba * 2 + abc * 2) % MOD;
            aba = tmp;
        }

        return (int) ((abc + aba) % MOD);
    }
}