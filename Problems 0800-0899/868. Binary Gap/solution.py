class Solution:
    def binaryGap(self, n: int) -> int:
        prev = -1
        i = 0
        res = 0
        while n != 0:
            bit = n & 1
            if bit == 1:
                if prev != -1:
                    gap = i - prev
                    res = max(res, gap)
                prev = i
            n >>= 1
            i += 1
        return res