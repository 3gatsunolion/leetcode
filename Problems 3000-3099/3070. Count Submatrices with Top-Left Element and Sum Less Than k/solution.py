class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        # m, n = len(grid), len(grid[0])

        # count = 0
        # for i in range(m):
        #     for j in range(n):
        #         if i > 0:
        #             grid[i][j] += grid[i - 1][j]
        #         if j > 0:
        #             grid[i][j] += grid[i][j - 1]
        #         if i > 0 and j > 0:
        #             grid[i][j] -= grid[i - 1][j - 1]
                
        #         if grid[i][j] <= k:
        #             count += 1
        #         else:
        #             # Sum will only increase from here
        #             break
        
        # return count

        m, n = len(grid), len(grid[0])
        cols = [0] * n

        count = 0
        for i in range(m):
            submatrix_sum = 0
            for j in range(n):
                cols[j] += grid[i][j]
                submatrix_sum += cols[j]

                if submatrix_sum <= k:
                    count += 1
                else:
                    break
        
        return count