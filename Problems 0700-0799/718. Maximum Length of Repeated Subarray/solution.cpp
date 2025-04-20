class Solution {
public:
    int findLength(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();

        if (m < n) return findLength(nums2, nums1);

        vector<int> dp(n + 1, 0);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = n-1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[j+1] = 1 + dp[j];
                } else {
                    dp[j+1] = 0;
                }
                res = max(res, dp[j+1]);
            }
        }
        return res;
        // vector<vector<int>> dp(m+1, vector<int>(n+1, 0));

        // int res = 0;
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (nums1[i] == nums2[j]) {
        //             dp[i+1][j+1] = 1 + dp[i][j];
        //         }
        //         res = max(res, dp[i+1][j+1]);
        //     }
        // }
        // return res;
    }
};