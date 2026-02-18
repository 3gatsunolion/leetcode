class Solution {
    public int triangleNumber(int[] nums) {
        // Triangle Inequality Theorem: 
        // The sum of any two sides must be greater than the third side.
        // Test all three combinations (a + b > c, a + c > b, and b + c > a).
        // If all three statements are true, the lengths can form a triangle
        // If even one statement is false, they cannot
        
        // Sort, so we start from largest number and find all pairs
        // to the left (so they are <=) that form valid triangles
        // Let's say the numbers are a, b, c, where a <= b <= c.
        // and the indices are ai, bi, ci respectively.
        // - We know b + c > a, since a <= b <= c
        // - a + c > b too since a <= b <= c
        // So we only need to check a + b > c
        // 1. If a + b <= c, then we increase ai to increase value of a
        // 2. If a + b > c, then we know all a values from ai to bi - 1
        // will form a valid triangle
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0;
            int r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
}