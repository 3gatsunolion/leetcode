class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        # bitwise OR will only increase 1s set
        # n^2 possible subarrays

        # dp contains the possible bitwise OR subarrays ending in i
        dp = set()

        res = set()
        for num in arr:
            curr = {num}
            for bitOR in dp:
                curr.add(bitOR | num)
            dp = curr
            res |= dp
        
        return len(res)