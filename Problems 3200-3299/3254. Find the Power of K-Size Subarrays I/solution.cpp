class Solution {
public:
    vector<int> resultsArray(vector<int>& nums, int k) {
        int count = 0;
        vector<int> res;
        for (int i = 0; i < nums.size(); i++) {
            if (i == 0 || (i > 0 && nums[i] == nums[i-1]+1)) {
                count++;
            } else {
                count = 1;
            }

            if (i >= k-1) {
                if (count >= k) {
                    res.push_back(nums[i]);
                } else {
                    res.push_back(-1);
                }
            }
        }
        return res;
    }
};