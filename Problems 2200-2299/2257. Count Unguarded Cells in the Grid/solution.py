class Solution:
    def countUnguarded(self, m: int, n: int, guards: List[List[int]], walls: List[List[int]]) -> int:
        grid = [[0]*n for _ in range(m)]
        dirs = [0, -1, 0, 1, 0]

        for i, j in guards:
            grid[i][j] = 2

        for i, j in walls:
            grid[i][j] = 3

        for i, j in guards:
            for step in range(4):
                direction = [dirs[step], dirs[step+1]]
                self.go(i, j, m, n, grid, direction)
        
        count = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    count += 1
        return count

    def go(self, i, j, m, n, grid, direction):
        r, c = i + direction[0], j + direction[1]
        if r < 0 or c < 0 or r >= m or c >= n or \
        grid[r][c] > 1:
            return
        grid[r][c] = 1
        self.go(r, c, m, n, grid, direction)
