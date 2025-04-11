class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        m, n = len(nums1), len(nums2)

        if m > n:
            return self.findMedianSortedArrays(nums2, nums1)

        targetNum = (m + n + 1) // 2
        low = 0
        high = m

        # Finding not on index, but searching partition size
        while low <= high:
            numOnLeftSide1 = low + (high - low) // 2
            numOnLeftSide2 = targetNum - numOnLeftSide1

            l1 = float('-inf')
            l2 = float('-inf')
            r1 = float('inf')
            r2 = float('inf')
            if numOnLeftSide1 > 0:
                l1 = nums1[numOnLeftSide1 - 1]
            if numOnLeftSide2 > 0:
                l2 = nums2[numOnLeftSide2 - 1]
            if numOnLeftSide1 < m:
                r1 = nums1[numOnLeftSide1]
            if numOnLeftSide2 < n:
                r2 = nums2[numOnLeftSide2]

            if l1 <= r2 and l2 <= r1:
                if (m + n) % 2 == 0:
                    return (max(l1, l2) + min(r1, r2)) / 2.0
                else:
                    return max(l1, l2)
            elif l1 > r2:
                high = numOnLeftSide1 - 1
            else:
                low = numOnLeftSide1 + 1
