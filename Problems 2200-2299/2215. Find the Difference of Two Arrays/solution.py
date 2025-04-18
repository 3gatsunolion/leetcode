class Solution:
    def findDifference(self, nums1: List[int], nums2: List[int]) -> List[List[int]]:
        nums1, nums2 = set(nums1), set(nums2)

        res = [[], []]
        for num in nums1:
            if num not in nums2:
                res[0].append(num)
        
        for num in nums2:
            if num not in nums1:
                res[1].append(num)
        
        return res