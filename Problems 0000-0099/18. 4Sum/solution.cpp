class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        return kSum(nums, target, 4);
    }

    vector<vector<int>> kSum(vector<int>& nums, int target, int k) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        vector<int> currNums;
        kSumHelper(nums, target, k, 0, currNums, res);
        return res;
    }

    void kSumHelper(vector<int>& nums, long long target, int k, int start, vector<int>& currNums, vector<vector<int>>& res) {
        int n = nums.size();
        if (k == 2) {
            int l = start, r = n - 1;
            while (l < r) {
                int total = nums[l] + nums[r];
                if (total == target) {
                    vector<int> kNums = currNums;
                    kNums.insert(kNums.end(), { nums[l], nums[r] });
                    res.push_back(kNums);
                    while (l < r && nums[l] == nums[l+1]) {
                        l++;
                    }
                    l++;

                    while (l < r && nums[r] == nums[r-1]) {
                        r--;
                    }
                    r--;
                } else if (total < target) {
                    l++;
                } else {
                    r--;
                }
            }
        } else {
            for (int i = start; i < n - k + 1; i++) {
                if (i > start && nums[i] == nums[i-1]) continue;

                if (target < (long long)nums[i]*k || target > (long long)nums[n-1]*k) break;

                currNums.push_back(nums[i]);
                kSumHelper(nums, target-nums[i], k-1, i+1, currNums, res);
                currNums.pop_back();
            }
        }
    }
};