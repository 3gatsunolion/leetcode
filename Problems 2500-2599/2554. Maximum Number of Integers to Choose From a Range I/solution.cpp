class Solution {
public:
    int maxCount(vector<int>& banned, int n, int maxSum) {
        // max is obviously n -> choose number 1 -> n
        // start off with 1 -> n, removing banned
        // if sum is <= maxSum, then return
        // else we need to remove minimum to get <= maxSum
        // we can just remove largest number, since that
        // will give us higher chance of getting <= maxSum

        unordered_set<int> bannedNums(banned.begin(), banned.end());

        vector<int> nums;
        int count = 0;
        for (int num = 1; num <= n; num++) {
            if (bannedNums.count(num)) {
                continue;
            }
            if (num > maxSum) break;
            maxSum -= num;
            count++;
        }

        return count;
    }
};