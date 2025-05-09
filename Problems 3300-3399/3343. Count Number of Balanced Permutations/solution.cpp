class Solution {
    int MOD = 1e9 + 7;
public:
    int countBalancedPermutations(string num) {
        int total = 0;
        vector<int> freq(10, 0);
        for (char c : num) {
            total += c - '0';
            freq[c-'0']++;
        }

        if (total % 2) return 0;

        int n = num.size();
        int target = total / 2;
        int evenSlots = (n + 1) / 2;

        vector<vector<vector<int>>> dp(10, vector<vector<int>>(target+1, vector<int>(evenSlots + 1, -1)));
        
        // comb[i][j] = Number of ways to place j same items in i slots
        vector<vector<int>> comb(evenSlots + 1, vector<int>(evenSlots + 1));

        for (int i = 0; i <= evenSlots; i++) {
            comb[i][i] = comb[i][0] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]) % MOD;
            }
        }

        return dfs(0, 0, evenSlots, n, target, freq, comb, dp);
    }

    int dfs(int digit, int currSum, int evenSlotsLeft, int slotsLeft, int& target, vector<int>& freq, vector<vector<int>>& comb, vector<vector<vector<int>>>& dp) {
        if (evenSlotsLeft < 0 || currSum > target || slotsLeft < evenSlotsLeft) return 0;

        if (digit == 10) return currSum == target && evenSlotsLeft == 0;

        if (dp[digit][currSum][evenSlotsLeft] != -1) return dp[digit][currSum][evenSlotsLeft];
        
        // come up with different combinations. let's say there's 5
        // 1 characters. then we can have 1 and 4 in even and odd
        // indexes respectively, etc.
        int oddSlotsLeft = slotsLeft - evenSlotsLeft;
        long long res = 0;
        for (int even = 0; even <= min(evenSlotsLeft, freq[digit]); even++) {
            if (freq[digit] - even > oddSlotsLeft) continue;
            int odd = freq[digit] - even;
            long long ways = (1LL*comb[evenSlotsLeft][even]*comb[oddSlotsLeft][odd]) % MOD;
            res = (res + (ways*dfs(digit + 1, currSum + even*digit, evenSlotsLeft-even, slotsLeft - freq[digit], target, freq, comb, dp) % MOD)) % MOD;
        }

        return dp[digit][currSum][evenSlotsLeft] = res;
    }
};