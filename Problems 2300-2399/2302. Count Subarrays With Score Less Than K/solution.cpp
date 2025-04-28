class Solution {
    public:
        long long countSubarrays(vector<int>& nums, long long k) {
            int left = 0;
            long long currSum = 0;
            long long res = 0;
            for (int right = 0; right < nums.size(); right++) {
                currSum += nums[right];
                while (currSum * (right - left + 1) >= k) {
                    currSum -= nums[left++];
                }
                res += right - left + 1;
            }
            return res;
        }
    };