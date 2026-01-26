class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.rank = [0]*n
        self.numSets = n

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def unionRank(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)

        if rootX == rootY:
            return

        if self.rank[rootX] < self.rank[rootY]:
            rootX, rootY = rootY, rootX
        
        self.parent[rootY] = rootX
        if self.rank[rootX] == self.rank[rootY]:
            self.rank[rootX] += 1

        self.numSets -= 1

DIRS = [0, -1, 0, 1, 0]
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])

        uf = UnionFind(m*n)

        water = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == "1":
                    for step in range(4):
                        x = i + DIRS[step]
                        y = j + DIRS[step + 1]
                        if x < 0 or y < 0 or x == m or y == n or grid[x][y] != "1":
                            continue
                        uf.unionRank(i*n+j, x*n+y)
                else:
                    water += 1

        return uf.numSets - water