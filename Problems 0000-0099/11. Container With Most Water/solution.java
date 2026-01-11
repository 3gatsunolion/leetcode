class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int res = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int water = (right - left) * h;

            if (water > res) {
                res = water;
            }

            // if (height[left] > height[right]) {
            //     right--;
            // } else {
            //     left++;
            // }
            while (left < right && height[left] <= h) {
                left++;
            }

            while (left < right && height[right] <= h) {
                right--;
            }
        }

        return res;
    }
}