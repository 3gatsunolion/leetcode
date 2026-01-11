class Solution {
    public:
        vector<int> searchRange(vector<int>& nums, int target) {
            int n = nums.size();
    
            if (n == 0) return {-1, -1};
    
            int first = bisectLeft(nums, target);
            if (first == n || nums[first] != target) {
                return {-1, -1};
            }
            int last = bisectRight(nums, target) - 1;
            return {first, last};
        }
    
        int bisectLeft(vector<int>& nums, int target) {
            int lo = 0;
            int hi = nums.size();
    
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
    
            return lo;
        }
    
        int bisectRight(vector<int>& nums, int target) {
            int lo = 0;
            int hi = nums.size();
    
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] <= target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
    
            return lo;
        }
    };