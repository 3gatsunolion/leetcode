class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> piles = new ArrayList<Integer>();

        for (int num : nums) {
            int i = binarySearch(piles, num);
            if (i == piles.size()) {
                piles.add(num);
            } else {
                piles.set(i, num);
            }
        }

        return piles.size();

        // int n = nums.length;
        // int[] dp = new int[n];

        // int res = 0;
        // for (int i = 0; i < n; i++) {
        //     dp[i] = 1;
        //     for (int j = 0; j < i; j++) {
        //         if (nums[j] < nums[i]) {
        //             dp[i] = Math.max(dp[i], 1 + dp[j]);
        //         }
        //     }
        //     res = Math.max(res, dp[i]);
        // }

        // return res;
    }

    private int binarySearch(List<Integer> nums, int target) {
        int lo = 0;
        int hi = nums.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}