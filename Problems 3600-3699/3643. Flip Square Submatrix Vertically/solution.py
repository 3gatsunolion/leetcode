class Solution:
    def reverseSubmatrix(self, grid: List[List[int]], x: int, y: int, k: int) -> List[List[int]]:
        lo = x
        hi = x + k - 1

        while lo < hi:
            for col in range(y, y + k):
                grid[lo][col], grid[hi][col] = grid[hi][col], grid[lo][col]
            lo += 1
            hi -= 1
        
        return grid