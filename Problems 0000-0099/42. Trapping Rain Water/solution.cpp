class Solution {
    public:
        int trap(vector<int>& height) {
            int res = 0;
            int maxLeft = 0;
            int maxRight = 0;
            int l = 0;
            int r = height.size() - 1;
            while (l < r) {
                // height[r] is maxRight and maxRight > maxLeft
                if (height[l] < height[r]) {
                    if (height[l] > maxLeft) {
                        maxLeft = height[l];
                    }
                    res += maxLeft - height[l];
                    l++;
                } else {
                    if (height[r] > maxRight) {
                        maxRight = height[r];
                    }
                    res += maxRight - height[r];
                    r--;
                }
            }
            return res;
        }
    };