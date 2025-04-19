class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.rank = [0]*n
        # two's complement of -1 is 1111111111
        self.cost = [-1]*n

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x, y, weight):
        rootX = self.find(x)
        rootY = self.find(y)

        # Could have more than one edge between two same nodes
        # if rootX == rootY: return

        if self.rank[rootX] < self.rank[rootY]:
            rootX, rootY = rootY, rootX
        
        self.parent[rootY] = rootX
        if self.rank[rootX] == self.rank[rootY]:
            self.rank[rootX] += 1
        
        self.cost[rootX] = self.cost[rootX] & self.cost[rootY] & weight

    def minCost(self, x, y):
        if x == y: return 0
        rootX = self.find(x)
        rootY = self.find(y)

        if rootX != rootY:
            return -1
        
        return self.cost[rootX]

class Solution:
    def minimumCost(self, n: int, edges: List[List[int]], query: List[List[int]]) -> List[int]:
        # bitwising numbers will always result in <= result,
        # so we can just take bitwise and of the weights in
        # each group as the minimum
        # and each group means it is possible to visit each
        # node in that group since it's connected
        uf = UnionFind(n)
        for u, v, w in edges:
            uf.union(u, v, w)
        
        res = []
        for u, v in query:
            res.append(uf.minCost(u, v))
        
        return res