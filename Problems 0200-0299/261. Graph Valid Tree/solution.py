class Solution:
    # Valid Tree:
    # 1. n nodes -> exactly n - 1 edges
    # 2. Acyclical -> no cycles

    # # 1. DFS Time: O(V + E), Space: O(V + E)
    # def validTree(self, n: int, edges: List[List[int]]) -> bool:
    #     if len(edges) != n - 1:
    #         return False
        
    #     # Note: Since this is an undirected tree, we just need
    #     # to keep track of parent and visited
    #     # If this was a directed tree we would need an array
    #     # visited where there are different states:
    #     # 0 -> unvisited
    #     # 1 -> visited
    #     # 2 -> in process of visiting
    #     # This is because there could be a "circular" shape for
    #     # a directed graph, but it does not have a cycle
    #     # Example:
    #     # 0 -> 1 -> 2
    #     # 0 -> 3 -> 2
    #     # But for undirected graph, any "circular" shape is a cycle!
    #     def hasCycle(node, parent, graph, visited):
    #         if node in visited:
    #             return True

    #         visited.add(node)
    #         for adj in graph[node]:
    #             if adj == parent:
    #                 continue

    #             if hasCycle(adj, node, graph, visited):
    #                 return True
            
    #         return False
        
    #     graph = [[] for _ in range(n)]
    #     for u, v in edges:
    #         graph[u].append(v)
    #         graph[v].append(u)

    #     visited = set()
    #     return not hasCycle(0, -1, graph, visited) and len(visited) == n

    # # 2. BFS1 Time: O(V + E), Space: O(V + E)
    # def validTree(self, n: int, edges: List[List[int]]) -> bool:
    #     if len(edges) != n - 1:
    #         return False
        
    #     graph = [[] for _ in range(n)]
    #     for u, v in edges:
    #         graph[u].append(v)
    #         graph[v].append(u)

    #     q = deque([(0, -1)]) # (node, parent)
    #     visited = set()
    #     visited.add(0)

    #     while q:
    #         node, parent = q.popleft()
    #         for adj in graph[node]:
    #             if adj == parent: continue
    #             if adj in visited:
    #                 return False
                
    #             visited.add(adj)
    #             q.append((adj, node))
        
    #     return len(visited) == n

    # # 3. BFS2 Time: O(V + E), Space: O(V + E)
    # def validTree(self, n: int, edges: List[List[int]]) -> bool:
    #     if len(edges) != n - 1:
    #         return False

    #     # Crucial!! No edges in this case
    #     if n == 1:
    #         return True
        
    #     graph = [[] for _ in range(n)]
    #     degrees = [0] * n
    #     for u, v in edges:
    #         graph[u].append(v)
    #         graph[v].append(u)

    #         degrees[u] += 1
    #         degrees[v] += 1

    #     q = deque()

    #     # Add all leaves in q (degree = 1)
    #     # If an undirected graph has a cycle (like a triangle), the nodes in that cycle
    #     # will always have at least 2 neighbors. No matter how many leaves you peel off
    #     # from the "branches" of the graph, the nodes in the cycle will never drop to a degree of 1.
    #     # They will be stuck at 2 (or more) and will never enter your queue.
    #     for node in range(n):
    #         if degrees[node] == 1:
    #             q.append(node)

    #     visited = 0

    #     while q:
    #         node = q.popleft()
    #         visited += 1

    #         for adj in graph[node]:
    #             degrees[adj] -= 1

    #             if degrees[adj] == 1:
    #                 q.append(adj)
        
    #     return visited == n

    # 4. Union Find Time: O(V + some constant), Space: O(V)
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        if len(edges) != n - 1:
            return False

        parent = [i for i in range(n)]
        rank = [0 for _ in range(n)]

        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]
        
        def union(x, y):
            rootX = find(x)
            rootY = find(y)

            if rootX == rootY:
                return False
            
            if rank[rootX] < rank[rootY]:
                rootX, rootY = rootY, rootX
            
            parent[rootY] = rootX

            if rank[rootX] == rank[rootY]:
                rank[rootX] += 1
            
            return True
        
        for u, v in edges:
            # Already have same ancestor -> cycle!
            if not union(u, v):
                return False
        
        # Don't need to check for disjointed sets because of
        # len(edges) != n - 1 check
        return True