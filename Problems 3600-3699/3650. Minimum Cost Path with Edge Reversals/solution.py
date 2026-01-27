from heapq import heappush, heappop

class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        # Classic Dijkstra question
        graph = [[] for _ in range(n)]
        # reverseGraph = [[] for _ in range(n)]

        for u, v, w in edges:
            # Dijkstra never reuses the same node in a cheaper path
            # due to nature of the minHeap and costs only get bigger
            # (no negative weights)
            # once a node is visited, it’s never revisited again
            graph[u].append([v, w])
            graph[v].append([u, 2 * w])
            # reverseGraph[v].append([u, w])
        
        minCost = [float('inf')] * n
        minCost[0] = 0 # costs 0 to get to node 0 since we star there

        q = [(0, 0)] # cost, node
        while q:
            cost, node = heappop(q)
            
            # stale
            if minCost[node] < cost:
                continue
            
            if node == n - 1:
                return cost

            for adj, w in graph[node]:
                newCost = cost + w
                if minCost[adj] > newCost:
                    minCost[adj] = newCost
                    heappush(q, (newCost, adj))
            
            # for adj, w in reverseGraph[node]:
            #     newCost = cost + 2 * w
            #     if minCost[adj] > newCost:
            #         minCost[adj] = newCost
            #         heappush(q, (newCost, adj))

        return -1