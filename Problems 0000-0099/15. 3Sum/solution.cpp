class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<vector<int>> res;
        for (int i = 0; i < n - 2; i++) {
            // no duplicates
            if (i > 0 && nums[i] == nums[i-1]) continue;
            if (nums[i] > 0) break;
            int l = i+1;
            int r = n-1;
            while (l < r) {
                int total = nums[i] + nums[l] + nums[r];
                if (total == 0) {
                    res.push_back({nums[i], nums[l], nums[r]});
                    l++;
                    while (l < r && nums[l] == nums[l-1]) {
                        l++;
                    }
                    r--;
                    while (l < r && nums[r] == nums[r+1]) {
                        r--;
                    }
                } else if (total < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
};