class Solution {
    public:
        long long minSum(vector<int>& nums1, vector<int>& nums2) {
            long long sum1 = 0;
            long long zero1 = 0;
            for (int num : nums1) {
                sum1 += num;
                if (num == 0) zero1++;
            }
            long long sum2 = 0;
            long long zero2 = 0;
            for (int num : nums2) {
                sum2 += num;
                if (num == 0) zero2++;
            }
    
            // minimum total if replace 0's with 1
            long long minSum1 = sum1 + zero1;
            long long minSum2 = sum2 + zero2;
    
            if ((zero1 == 0 && minSum2 > minSum1) || (zero2 == 0 && minSum1 > minSum2)) {
                return -1;
            }
    
            return max(minSum1, minSum2);
        }
    };