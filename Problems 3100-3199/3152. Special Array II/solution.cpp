class Solution {
public:
    vector<bool> isArraySpecial(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<int> prefixSum(n, 0);
        int sameParityCount = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i]%2 == nums[i-1]%2) sameParityCount++;
            prefixSum[i] = sameParityCount;
        }
        
        vector<bool> res(queries.size());
        for (int i = 0; i < queries.size(); i++) {
            vector<int>& query = queries[i];
            res[i] = prefixSum[query[1]] == prefixSum[query[0]];

        }
        return res;
    }
    vector<bool> isArraySpecial2(vector<int>& nums, vector<vector<int>>& queries) {
        vector<int> bounds;
        int n = nums.size();
        int s = 0;
        for (int e = 1; e < n; e++) {
            // odd means they're different parity, even same
            if ((nums[e-1]+nums[e]) % 2 == 0) {
                bounds.push_back(s);
                bounds.push_back(e-1);
                s = e;
            }
        }
        bounds.push_back(s);
        bounds.push_back(n-1);

        vector<bool> res(queries.size(), false);
        for (int i = 0; i < queries.size(); i++) {
            int start = lower_bound(bounds.begin(), bounds.end(), queries[i][0]) - bounds.begin();
            int end = lower_bound(bounds.begin(), bounds.end(), queries[i][1]) - bounds.begin();
            if (start == end || (start % 2) == 0 && start == end-1) {
                res[i] = true;
            }
        }
        return res;
    }
};