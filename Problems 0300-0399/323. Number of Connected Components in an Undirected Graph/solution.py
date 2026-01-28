class Solution:
    # 1. DFS: Time O(V + E) Space: O(V + E)
    # def countComponents(self, n: int, edges: List[List[int]]) -> int:
    #     def dfs(node):
    #         visited[node] = True

    #         for adj in graph[node]:
    #             if not visited[adj]:
    #                 dfs(adj)
        
    #     graph = [[] for _ in range(n)]
    #     for u, v in edges:
    #         graph[u].append(v)
    #         graph[v].append(u)
        
    #     visited = [False] * n
    #     count = 0
    #     for node in range(n):
    #         if not visited[node]:
    #             dfs(node)
    #             count += 1
    #     return count

    # # 2. BFS
    # def countComponents(self, n: int, edges: List[List[int]]) -> int:
    #     def bfs(node):
    #         visited[node] = True
    #         q = deque([node])

    #         while q:
    #             curr = q.popleft()

    #             for adj in graph[curr]:
    #                 if not visited[adj]:
    #                     visited[adj] = True
    #                     q.append(adj)
        
    #     graph = [[] for _ in range(n)]
    #     for u, v in edges:
    #         graph[u].append(v)
    #         graph[v].append(u)
        
    #     visited = [False] * n
    #     count = 0
    #     for node in range(n):
    #         if not visited[node]:
    #             bfs(node)
    #             count += 1
    #     return count

    # 3. Union Find
    # Time: O(V + E*amortized(V))
    # Space: O(V)
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        parent = [i for i in range(n)]
        rank = [0] * n
        
        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]
        
        def union(x, y):
            rootX, rootY = find(x), find(y)

            if rootX == rootY:
                return False
            
            if rank[rootX] < rank[rootY]:
                rootX, rootY = rootY, rootX
            
            parent[rootY] = rootX

            if rank[rootX] == rank[rootY]:
                rank[rootX] += 1
            
            return True

        count = n
        for u, v in edges:
            if union(u, v):
                count -= 1
        
        return count
        
