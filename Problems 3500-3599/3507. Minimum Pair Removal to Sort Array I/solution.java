class Solution {
    public int minimumPairRemoval(int[] nums) {
        // Worst case: Keep replacing until only 1 element
        // left, in which case nums will be sorted
        int n = nums.length;
        int count = 0;
        while (n > 1) {
            int minSum = Integer.MAX_VALUE;
            int minIdx = -1;
            boolean isSorted = true;
            for (int i = 1; i < n; i++) {
                int sum = nums[i - 1] + nums[i];
                if (sum < minSum) {
                    minSum = sum;
                    minIdx = i - 1;
                }

                if (nums[i - 1] > nums[i]) {
                    isSorted = false;
                }
            }

            if (isSorted) {
                return count;
            }

            nums[minIdx] = minSum;
            for (int i = minIdx + 1; i < n - 1; i++) {
                nums[i] = nums[i + 1];
            }
            n--;
            count++;
        }
        return count;
    }
}