class Solution {
public:
    int operation1(int num) {
        return (num+1)/2;
    }

    int operation2(int num, int k) {
        return num - (k <= num ? num - k : num);
    }

    int minArraySum(vector<int>& nums, int k, int op1, int op2) {
        vector<vector<vector<int>>> dp(nums.size(), vector<vector<int>>(op1+1, vector<int>(op2+1, -1)));

        return findMin(0, nums, op1, op2, k, dp);
    }

    int findMin(int i, vector<int>& nums, int op1, int op2, int k,  vector<vector<vector<int>>>& dp) {
        if (i == nums.size()) {
            return 0;
        }

        if (dp[i][op1][op2] != -1) {
            return dp[i][op1][op2];
        }

        // don't do anything to current number
        int res = nums[i] + findMin(i+1, nums, op1, op2, k, dp);
        // perform op1 on nums[i]
        if (op1 > 0) {
            res = min(res, operation1(nums[i]) + findMin(i+1, nums, op1-1, op2, k, dp));
        }

        // perform op2 on nums[i]
        if (op2 > 0 and nums[i] >= k) {
            res = min(res, nums[i]-k + findMin(i+1, nums, op1, op2-1, k, dp));
        }

        // perform op1 and op2 on nums[i]
        if (op1 > 0 and op2 > 0) {
            int halved = operation1(nums[i]);
            if (halved >= k) {
                res = min(res, halved-k + findMin(i+1, nums, op1-1, op2-1, k, dp));
            }
        }

        if (op1 > 0 and op2 > 0 and nums[i] >= k) {
            int minus = nums[i]-k;
            res = min(res, operation1(minus) + findMin(i+1, nums, op1-1, op2-1, k, dp));
        }

        dp[i][op1][op2] = res;
        return dp[i][op1][op2];
    }
};