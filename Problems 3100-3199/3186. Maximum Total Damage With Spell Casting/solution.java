class Solution {
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        // dp[i] = max total damage from 0...i including i
        long[] dp = new long[n];
        dp[0] = power[0];
        long currMax = 0;
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (power[i] == power[i - 1]) {
                dp[i] = power[i] + dp[i - 1];
            } else {
                while (power[j] + 2 < power[i]) {
                    currMax = Math.max(currMax, dp[j++]);
                }
                dp[i] = power[i] + currMax;
            }
        }

        for (; j < n; j++) {
            currMax = Math.max(currMax, dp[j]);
        }
        return currMax;
    }
}