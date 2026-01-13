class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        n, ans = len(baskets), 0
        
        for fruit in fruits:
            placed = False
            for i in range(n):
                if fruit <= baskets[i]:
                    baskets[i] = 0
                    placed = True
                    break
            if not placed:
                ans += 1

        return ans