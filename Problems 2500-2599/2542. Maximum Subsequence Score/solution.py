class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        # we want to maximize both nums1 and nums2
        # max num from nums2 is kth largest, since we can't
        # get k+1th... to be minimum of k items
        nums = sorted([(a, b) for a, b in zip(nums1, nums2)],
                key=lambda x: x[1], reverse=True)
        
        # minheap to store nums1, since we want to maximize
        # sum of nums1, so we want to remove smallest num
        # in nums1 to try adding new num in nums1
        total, h = 0, []
        
        # starting with max nums2 val, then working our way down
        # and each iteration we maximize sum of nums1 by
        # popping off the minimum nums1 element
        res = 0
        for num1, num2 in nums:
            total += num1
            heappush(h, num1)
            if len(h) == k:
                res = max(res, total*num2)
                total -= heappop(h)
        
        return res
