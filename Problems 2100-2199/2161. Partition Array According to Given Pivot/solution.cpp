class Solution {
public:
    vector<int> pivotArray(vector<int>& nums, int pivot) {
        // int n = nums.size();
        int lo = 0, same = 0, hi = 0;

        for (int num : nums) {
            if (num < pivot) {
                lo++;
            } else if (num == pivot) {
                same++;
            }
        }
        
        vector<int> res(nums.size());
        hi = lo + same;
        same = lo;
        lo = 0;
        for (int num : nums) {
            if (num < pivot) {
                res[lo++] = num;
            } else if (num == pivot) {
                res[same++] = num;
            } else {
                res[hi++] = num;
            }
        }

        return res;
    }
};