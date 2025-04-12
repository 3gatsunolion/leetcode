class Solution {
public:
    int minOperations(vector<int>& nums) {
        // Start reverse, we want last element to be as big
        // as possible, so we never perform operation on last
        // element. Every element to the left from there
        // we want divide by greatest proper divisor until
        // it's <= prev element

        int n = nums.size();
        int res = 0;
        for (int i = n-2; i >= 0; i--) {
            if (nums[i] <= nums[i+1]) continue;
            for (int factor = 2; factor*factor <= nums[i]; factor++) {
                // we have to divide by greatest proper divisor
                // so we start from 2 and work our way up
                // if it's divisible by it, then we know
                // this is the smaller factor we get when
                // we divide by gpd
                if (nums[i] % factor == 0) {
                    nums[i] = factor;
                    res++;
                    break;
                }
            }
            if (nums[i] > nums[i+1]) return -1;
        }
        return res;
    }
};