class Solution {
    public:
        int minSubArrayLen(int target, vector<int>& nums) {
            int n = nums.size();
            int res = n + 1;
            int currSum = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                currSum += nums[right];
    
                while (currSum >= target) {
                    res = min(res, right - left + 1);
                    currSum -= nums[left++];
                }
    
                if (res == 1) return res;
            }
    
            return res % (n + 1);
        }
    };