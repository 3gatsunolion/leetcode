class Solution {
public:
    int minZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<int> deltas(n+1, 0);
        int k = 0;
        int delta = 0;
        for (int i = 0; i < n; i++) {
            delta += deltas[i];
            // keep going until we get nums[i] == 0
            while (delta < nums[i]) {
                if (k == queries.size()) return -1;
                int l = queries[k][0];
                int r = queries[k][1];
                int val = queries[k][2];
                k++;

                // don't need to use this one, no effect here
                if (r < i) continue;
                if (l <= i) {
                    delta += val;
                } else {
                    deltas[l] += val;
                }
                deltas[r+1] -= val;
            }
        }
        return k;
    }

    // int minZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
    //     int lo = 0, hi = queries.size();
    //     while (lo < hi) {
    //         int numQueries = lo + (hi - lo) / 2;
    //         if (canMakeZero(nums, queries, numQueries)) {
    //             hi = numQueries;
    //         } else {
    //             lo = numQueries + 1;
    //         }
    //     }

    //     return canMakeZero(nums, queries, lo) ? lo : -1;
    // }

    bool canMakeZero(vector<int>& nums, vector<vector<int>>& queries, int k) {
        int n = nums.size();
        vector<int> line(n+1, 0);

        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];
            line[l] -= val;
            if (r < n - 1) {
                line[r+1] += val;
            }
        }

        int currDelta = 0;
        for (int i = 0; i < n; i++) {
            currDelta += line[i];
            if (nums[i] + currDelta > 0) return false;
        }
        return true;
    }
};