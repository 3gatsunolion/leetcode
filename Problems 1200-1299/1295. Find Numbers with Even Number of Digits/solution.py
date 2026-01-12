class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        res = 0
        for num in nums:
            if self.numDigits(num) % 2 == 0:
                res += 1
        return res
    
    def numDigits(self, num):
        res = 0
        while num != 0:
            num //= 10
            res += 1
        return res