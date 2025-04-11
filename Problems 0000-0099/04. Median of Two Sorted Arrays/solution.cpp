class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // num from nums1
        int lo = 0, hi = m;
        int numOnLeftSide = (m + n + 1) / 2;
        while (lo <= hi) {
            int mid1 = lo + (hi - lo) / 2;
            int mid2 = numOnLeftSide - mid1;

            int l1 = mid1 > 0 ? nums1[mid1-1] : INT_MIN, r1 = mid1 < m ? nums1[mid1] : INT_MAX;
            int l2 = mid2 > 0 ? nums2[mid2-1] : INT_MIN, r2 = mid2 < n ? nums2[mid2] : INT_MAX;
            if (l1 <= r2 && l2 <= r1) {
                // correct partition!
                if ((m + n) % 2 == 0) {
                    return (double)(max(l1, l2) + min(r1, r2)) / 2.0;
                }
                return max(l1, l2);
            } else if (l1 > r2) {
                hi = mid1 - 1;
            } else {
                lo = mid1 + 1;
            }

        }
        return -1;
    }
};