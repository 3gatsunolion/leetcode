class Solution {
public:
    int deleteAndEarn(vector<int>& nums) {
        int maxVal = *max_element(nums.begin(), nums.end());
        vector<int> earn(maxVal+1, 0);
        for (int num : nums) {
            earn[num] += num;
        }

        int take = 0, skip = 0;
        for (int i = 0; i <= maxVal; i++) {
            int tmp = earn[i] + skip;
            skip = max(take, skip);
            take = tmp;
        }
        return max(take, skip);
    }
};