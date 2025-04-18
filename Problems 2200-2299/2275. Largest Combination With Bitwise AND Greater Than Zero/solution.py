class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        
        # for a&b&c&...z to > 0, then all of them must
        # share at least one common bit
        # since candidates[i] is between 1 and 10^7 (24 bits)
        res = 0
        for i in range(24):
            bit = 1 << i
            count = 0
            for candidate in candidates:
                if bit & candidate != 0:
                    count += 1
            res = max(res, count)
        return res