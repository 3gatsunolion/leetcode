from collections import Counter

class FindSumPairs:

    def __init__(self, nums1: List[int], nums2: List[int]):
        self.freq1 = Counter(nums1)
        self.freq2 = Counter(nums2)
        self.nums2 = nums2

    def add(self, index: int, val: int) -> None:
        old = self.nums2[index]
        self.freq2[old] -= 1
        # if self.freq2[old] == 0:
        #     del self.freq2[old]
        
        newVal = old + val
        self.nums2[index] = newVal
        self.freq2[newVal] += 1
        
    def count(self, tot: int) -> int:
        res = 0
        for num1, count1 in self.freq1.items():
            res += self.freq2[(tot - num1)]*count1
        return res


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)