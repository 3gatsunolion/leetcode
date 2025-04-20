class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        currEnd = -1
        res = 0
        for i, num in enumerate(arr):
            currEnd = max(num, currEnd)
            if i == currEnd:
                res += 1
        return res