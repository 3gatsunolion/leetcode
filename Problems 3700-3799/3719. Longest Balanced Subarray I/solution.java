class Solution {
    // Brute force: O(n^2)
    public int longestBalanced(int[] nums) {
        Set<Integer> evens = new HashSet<>();
        Set<Integer> odds = new HashSet<>();
        int n = nums.length;
        int res = 0;
        for (int l = 0; l < n; l++) {
            evens.clear();
            odds.clear();
            for (int r = l; r < n; r++) {
                if ((nums[r] & 1) == 0) {
                    evens.add(nums[r]);
                } else {
                    odds.add(nums[r]);
                }

                if (evens.size() == odds.size()) {
                    res = Math.max(r - l + 1, res);
                }
            }
        }

        return res;
    }
}