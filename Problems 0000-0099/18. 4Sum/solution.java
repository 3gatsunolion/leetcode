class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        kSum(nums, 0, target, 4, new ArrayList<>(), res);
        return res;
    }

    private void kSum(int[] nums, int start, long target, int k, List<Integer> path, List<List<Integer>> res) {
        int n = nums.length;
        int end = n - 1;
        if (end - start + 1 < k || k < 2 || target < (long) nums[start] * k || target > (long) nums[end] * k) {
            // NOTE: (double) so that there's no integer overflow (overflow
            // results in negative number)
            return;
        }

        if (k == 2) {
            // Two sum
            int l = start;
            int r = end;
            while (l < r) {
                int total = nums[l] + nums[r];
                if (total == target) {
                    List<Integer> toAdd = new ArrayList(path);
                    toAdd.add(nums[l]);
                    toAdd.add(nums[r]);

                    res.add(toAdd);

                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    l++;

                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                } else if (total < target) {
                    l++;
                } else {
                    r--;
                }
            }
        } else {
            for (int i = start; i < n - (k - 1); i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                // IMPORTANT: This will only work for all positive numbers
                // Since negative + negative = MORE negative wouldn't work
                // if (nums[i] > target) {
                //     break;
                // }

                path.add(nums[i]);
                kSum(nums, i + 1, target - nums[i], k - 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
    // public List<List<Integer>> fourSum(int[] nums, int target) {
    //     Arrays.sort(nums);
    //     return kSum(nums, 0, target, 4);
    // }

    // private List<List<Integer>> kSum(int[] nums, int start, long target, int k) {
    //     List<List<Integer>> res = new ArrayList<>();
    //     int n = nums.length;
    //     int end = n - 1;
    //     if (end - start + 1 < k || k < 2 || target / k < nums[start] || target / k > nums[end]) {
    //         return res;
    //     }

    //     if (k == 2) {
    //         // Two sum
    //         int l = start;
    //         int r = end;
    //         while (l < r) {
    //             int total = nums[l] + nums[r];
    //             if (total == target) {
    //                 res.add(Arrays.asList(nums[l], nums[r]));

    //                 while (l < r && nums[l] == nums[l + 1]) {
    //                     l++;
    //                 }
    //                 l++;

    //                 while (l < r && nums[r] == nums[r - 1]) {
    //                     r--;
    //                 }
    //                 r--;
    //             } else if (total < target) {
    //                 l++;
    //             } else {
    //                 r--;
    //             }
    //         }
    //     } else {
    //         for (int i = start; i < n - (k - 1); i++) {
    //             if (i > start && nums[i] == nums[i - 1]) {
    //                 continue;
    //             }

    //             // if (nums[i] > target) {
    //             //     break;
    //             // }

    //             List<List<Integer>> tmp = kSum(nums, i + 1, target - nums[i], k - 1);
                
    //             for (List<Integer> t : tmp) {
    //                 List<Integer> toAdd = new ArrayList(t);
    //                 toAdd.add(0, nums[i]);
    //                 res.add(toAdd);
    //             }
    //         }
    //     }
    //     return res;
    // }
}