class Solution {
public:
    int minDays(int n) {
        unordered_map<int, int> dp;

        return dfs(n, dp);
    }

    int dfs(int n, unordered_map<int, int>& dp) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (dp.find(n) != dp.end()) return dp[n];
        int half = n % 2 + dfs(n/2, dp);
        int third = n % 3 + dfs(n/3, dp);
        return dp[n] = 1 + min(half, third);
    }
};