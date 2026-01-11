class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // Optimization: Since sorted nums[i] is smallest number,
            // so if it's > 0, then sum will always > 0
            if (nums[i] > 0) {
                break;
            }
            // No duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int total = nums[i] + nums[l] + nums[r];
                if (total == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    // No duplicates
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;

                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                } else if (total < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}