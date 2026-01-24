class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        # If we go left to right in place, it will override values in nums1
        # So we can work from right to left, since nums1 and nums2 are
        # both sorted already
        i1 = m - 1
        i2 = n - 1

        # If we've written all of nums2 in place, then we can stop
        # since we're writing in nums1
        i = m + n - 1
        while i2 >= 0:
            if i1 >= 0 and nums1[i1] > nums2[i2]:
                nums1[i] = nums1[i1]
                i1 -= 1
            else:
                nums1[i] = nums2[i2]
                i2 -=1
            i -= 1
