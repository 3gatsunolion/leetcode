class BIT:
    def __init__(self, n):
        self.sums = [0]*(n+1)

    def query(self, i):
        res = 0
        while i > 0:
            res += self.sums[i]
            i -= i & -i
        return res
    
    def update(self, i, delta):
        n = len(self.sums)
        while i < n:
            self.sums[i] += delta
            i += i & -i

class Solution:
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        bit = BIT(n)

        nums1IndexMap = { num: i for i, num in enumerate(nums1) }

        res = 0
        for i, num in enumerate(nums2):
            nums1Index = nums1IndexMap[num]

            # Nums that appear before num in both nums1 and nums2
            numLeft = bit.query(nums1Index)
            # Nums that appear after num in nums1 but appears
            # before num in nums2, so we cannot include
            numRightInOnlyNums1 = i - numLeft
            numRight = (n - nums1Index - 1) - numRightInOnlyNums1         
            res += numLeft*numRight
            bit.update(nums1Index+1, 1)
        
        return res

    # def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
    #     n = len(nums1)
    #     bit1 = BIT(n)
    #     bit2 = BIT(n)

    #     nums1IndexMap = { num: i for i, num in enumerate(nums1) }

    #     # If you call bit1.query(i) -> this returns amount of
    #     # numbers between indices [0,..., i] that have appeared
    #     # so far
    #     # bit2.query(i) -> returns number of pairs < than index
    #     # i in nums1 AND nums2 that has appeared already

    #     res = 0
    #     for num in nums2:
    #         num1Index = nums1IndexMap[num]
    #         res += bit2.query(num1Index)
    #         # mark num1Index number as seen
    #         bit1.update(num1Index+1, 1)
    #         # update how many unique pairs to form with
    #         # number at num1Index from BOTH nums1 and nums2
    #         numPairs = bit1.query(num1Index)
    #         bit2.update(num1Index+1, numPairs)
        
    #     return res
