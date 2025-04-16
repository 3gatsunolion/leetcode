class Solution {
public:
    int minCapability(vector<int>& nums, int k) {
        int lo = *min_element(nums.begin(), nums.end());
        int hi = *max_element(nums.begin(), nums.end());

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // can steal k houses where maximum amount
            // of money from one house stolen is <= mid
            if (canSteal(nums, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    bool canSteal(vector<int>& nums, int k, int capability) {
        int n = nums.size();
        for (int i = 0; i < n;) {
            if (nums[i] <= capability) {
                i += 2;
                k--;
            } else {
                i++;
            }

            if (k == 0) return true;
        }
        return false;
    }
};