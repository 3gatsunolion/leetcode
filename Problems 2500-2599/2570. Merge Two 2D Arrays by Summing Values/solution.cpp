class Solution {
public:
    vector<vector<int>> mergeArrays(vector<vector<int>>& nums1, vector<vector<int>>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<vector<int>> res;
        for (int i1=0, i2=0; i1 < m || i2 < n; ) {
            if (i1 < m && i2 < n) {
                if (nums1[i1][0] < nums2[i2][0]) {
                    res.push_back(nums1[i1]);
                    i1++;
                } else if (nums1[i1][0] > nums2[i2][0]) {
                    res.push_back(nums2[i2]);
                    i2++;
                } else {
                    res.push_back({ nums1[i1][0], nums1[i1][1] + nums2[i2][1] });
                    i1++;
                    i2++;
                }
            } else if (i1 < m) {
                res.push_back(nums1[i1]);
                i1++;
            } else if (i2 < n) {
                res.push_back(nums2[i2]);
                i2++;
            }
        }

        return res;
    }
};