class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int target = 0;
        long sum = 0;
        for (int num : nums) {
            target = (target + num) % p;
            sum += num;
        }

        if (target == 0) return 0;

        if (sum < p) return -1;

        Map<Integer, Integer> lastSeen = new HashMap<>();
        lastSeen.put(0, -1);

        int res = n;
        int curr = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] % p) == target) {
                return 1;
            }
            curr = (curr + nums[i]) % p;
            lastSeen.put(curr, i);

            // curr - x = target -> curr - target = x
            int want = (curr - target + p) % p;
            res = Math.min(res, i - lastSeen.getOrDefault(want, -n));
        }

        return res < n ? res : -1;
    }
}