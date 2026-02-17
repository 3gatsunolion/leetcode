class Solution:
    def findKthNumber(self, n: int, k: int) -> int:
        # 1, 2, 3, 4, 5, 6, 7, 8, 9
        # 10,...,19 | 20
        # 100,...,109 | 110

        # Start off at 1. 1 has its own tree. Calculate number of nodes
        # in tree to see if k falls in this tree. If not. We can go to 2.
        # If k falls within tree, we go down to 10 and calculate number of
        # nodes in that tree. Rinse and repeat.

        # Calc steps in this level
        def countNodes(start, end, n):
            count = 0
            while start <= n:
                count += min(n + 1, end) - start
                start *= 10
                end *= 10
            return count

        curr = 1
        k -= 1
        while k > 0:
            count = countNodes(curr, curr + 1, n)
            if count <= k:
                curr += 1
                k -= count
            else:
                curr *= 10
                k -= 1
        
        return curr