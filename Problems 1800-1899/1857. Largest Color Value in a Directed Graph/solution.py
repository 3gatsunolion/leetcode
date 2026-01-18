class Solution:
    def largestPathValue(self, colors: str, edges: List[List[int]]) -> int:
        n = len(colors)
        graph = [[] for _ in range(n)]
        for a, b in edges:
            graph[a].append(b)

        # dp[i][j] -> number of j color in path starting at i
        dp = [[0]*26 for _ in range(n)]
        visited = [0]*n

        res = 0
        for node in range(n):
            if self.solve(colors, node, graph, dp, visited):
                return -1
            res = max(res, max(dp[node]))
        return res
        
    def solve(self, colors, node, graph, dp, visited):
        if visited[node] == -1:
            return True
        
        if visited[node] == 1:
            return False
        
        visited[node] = -1
        for adj in graph[node]:
            if self.solve(colors, adj, graph, dp, visited):
                return True
            for color in range(26):
                dp[node][color] = max(dp[node][color], dp[adj][color])

        visited[node] = 1
        dp[node][ord(colors[node])-ord('a')] += 1
        return False