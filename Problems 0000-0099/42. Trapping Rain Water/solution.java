class Solution {
    public int trap(int[] height) {
        int maxLeftHeightSoFar = 0;
        int maxRightHeightSoFar = 0;

        int l = 0;
        int r = height.length - 1;
        int water = 0;
        while (l < r) {
            // Note: Either height[l] and height[r] are the max height
            // we've seen so far
            // Height of water trapped is always bound by minimum between
            // maxLeftHeightSoFar and maxRightHeightSoFar, so we can
            // calculate from the side that has the min max height so far
            if (height[l] < height[r]) {
                if (height[l] > maxLeftHeightSoFar) {
                    maxLeftHeightSoFar = height[l++];
                } else {
                    water += maxLeftHeightSoFar - height[l++];
                }
            } else {
                if (height[r] > maxRightHeightSoFar) {
                    maxRightHeightSoFar = height[r--];
                } else {
                    water += maxRightHeightSoFar - height[r--];
                }
            }
        }

        return water;
    }
}