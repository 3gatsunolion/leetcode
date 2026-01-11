class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return numWaysToDecode(s, 0, dp);
    }

    private int numWaysToDecode(String s, int i, int[] dp) {
        if (i == s.length()) return 1;
        if (dp[i] != -1) return dp[i];
        if (s.charAt(i) == '0') return dp[i] = 0;
        dp[i] = numWaysToDecode(s, i + 1, dp);
        int ch = s.charAt(i);
        if (i < s.length() - 1 && (ch == '1' || ch == '2' && s.charAt(i + 1) < '7')) {
            dp[i] += numWaysToDecode(s, i + 2, dp);
        }
        return dp[i];
    }
    // public int numDecodings(String s) {
    //     int n = s.length();
    //     // No leading zeroes
    //     if (n == 0 || s.charAt(0) == '0') return 0; 
    //     int[] dp = new int[n + 1];
    //     dp[0] = dp[1] = 1;
    //     for (int i = 1; i < n; i++) {
    //         char prev = s.charAt(i - 1);
    //         char curr = s.charAt(i);
    //         if (curr == '0') {
    //             // Can only be paired with previous number
    //             if (prev != '1' && prev != '2') {
    //                 return 0;
    //             }
    //             dp[i + 1] = dp[i - 1];
    //         } else {
    //             dp[i + 1] = dp[i];
    //             if (prev == '1' || (prev == '2' && curr <= '6')) {
    //                 dp[i + 1] += dp[i - 1];
    //             }
    //         }
    //     }
    //     return dp[n];
    // }
}