class Solution {
    public:
        vector<int> twoSum(vector<int>& numbers, int target) {
            int lo = 0;
            int hi = numbers.size() - 1;
    
            while (lo < hi) {
                int total = numbers[lo] + numbers[hi];
                if (total == target) {
                    return {lo + 1, hi + 1};
                } else if (total < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
            
            return {lo + 1, hi + 1};
        }
    };