class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n)]
    
    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)

        if rootX == rootY:
            return
        
        if rootX < rootY:
            self.parent[rootY] = rootX
        else:
            self.parent[rootX] = rootY

class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        n = len(s1)
        uf = UnionFind(26)
        for i in range(n):
            uf.union(ord(s1[i])-ord('a'), ord(s2[i])-ord('a'))
        
        res = []
        for c in baseStr:
            i = uf.find(ord(c)-ord('a'))
            res.append(chr(i+ord('a')))
        
        return "".join(res)