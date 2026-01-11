class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])

        lo = 0
        hi = m - 1
        while lo < hi:
            mid = lo + (hi - lo) // 2
            if matrix[mid][-1] < target: 
                lo = mid + 1
            else:
                hi = mid
        
        i = bisect_left(matrix[lo], target)
        if i == n or matrix[lo][i] != target:
            return False
        return True