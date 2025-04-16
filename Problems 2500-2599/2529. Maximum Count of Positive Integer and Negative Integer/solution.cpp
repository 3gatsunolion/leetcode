class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int n = nums.size();
        int lo = lower_bound(nums.begin(), nums.end(), 0) - nums.begin();
        int hi = upper_bound(nums.begin(), nums.end(), 0) - nums.begin();
        return max(n - hi, lo);
    }
};