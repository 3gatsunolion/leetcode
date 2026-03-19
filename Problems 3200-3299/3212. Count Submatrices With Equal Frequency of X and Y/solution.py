class Solution:
    def numberOfSubmatrices(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])

        x_col_count = [0] * n
        y_col_count = [0] * n

        count = 0
        for i in range(m):
            x_count = 0
            y_count = 0
            for j in range(n):
                x_col_count[j] += grid[i][j] == 'X'
                y_col_count[j] += grid[i][j] == 'Y'
                x_count += x_col_count[j]
                y_count += y_col_count[j]

                if x_count > 0 and x_count == y_count:
                    count += 1
        
        return count