class Solution:
    def distributeCandies(self, n: int, limit: int) -> int:
        if (n // limit) > 3:
            return 0
        
        minCandyPerChild = max(0, n - 2*limit)
        maxCandyPerChild = min(limit, n)
        res = 0
        # Iterate over number of different values to give first slot
        for i in range(minCandyPerChild, maxCandyPerChild + 1):
            leftoverMin = max(0, n - i - limit)
            leftoverMax = min(limit, n - i)
            # Number of different values possible to give second slot
            res += leftoverMax - leftoverMin + 1
            # No need to think about third slot, it'll automatically
            # be given the remaining amount
        return res