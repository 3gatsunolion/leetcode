class Solution {
public:
    long long countFairPairs(vector<int>& nums, int lower, int upper) {
        // Change to find pairs where nums[i] + nums[j] <= k
        // And call that function twice on upper and lower
        sort(nums.begin(), nums.end());
        return countLessThan(nums, upper+1) - countLessThan(nums, lower);
    }

    long long countLessThan(vector<int>& nums, int k) {
        int n = nums.size();

        int l = 0, r = n - 1;
        long long count = 0;
        while (l < r) {
            int total = nums[l] + nums[r];
            if (total < k) {
                // all right boundary from l+1 -> r
                // will be valid pairs with l
                count += r - l;
                l++;
            } else {
                r--;
            }
        }
        return count;
    }
};