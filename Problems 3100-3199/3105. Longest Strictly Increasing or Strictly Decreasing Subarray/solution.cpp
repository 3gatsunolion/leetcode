class Solution {
public:
    int longestMonotonicSubarray(vector<int>& nums) {
        if (nums.size() == 0) return 0;
        
        int curr = 1, res = 1;
        bool isIncreasing = false;
        for (int i = 1; i < nums.size(); i++) {
            if (nums[i] > nums[i-1]) {
                curr = isIncreasing ? curr + 1 : 2;
                isIncreasing = true;
            } else if (nums[i] < nums[i-1]) {
                curr = isIncreasing ? 2 : curr + 1;
                isIncreasing = false;
            } else {
                curr = 1;
            }
            res = max(res, curr);
        }
        return res;
    }
};