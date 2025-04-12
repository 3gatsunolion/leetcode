class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        if (nums.size() < 3) return 0;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            // this is the closest to target in this iteration
            // no need to go further and try every combo
            int maxSum = nums[i] + nums[n-2] + nums[n-1];
            if (maxSum < target) {
                if (abs(target-maxSum) < abs(target - res)) {
                    res = maxSum;
                }
                continue;
            }

            // any further combo will only get bigger and further
            // away from target, so break
            int minSum = nums[i] + nums[i+1] + nums[i+2];
            if (minSum > target) {
                if (abs(target-minSum) < abs(target-res)) {
                    res = minSum;
                }
                break;
            }

            int l = i+1;
            int r = n-1;
            while (l < r) {
                int total = nums[i] + nums[l] + nums[r];
                if (abs(target - total) < abs(target - res)) {
                    res = total;
                }
                if (total == target) {
                    return total;
                } else if (total < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
};