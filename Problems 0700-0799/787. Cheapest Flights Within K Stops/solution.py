from heapq import heappop, heappush
class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        # # build graph
        # graph = defaultdict(list)
        # for city1, city2, price in flights:
        #     graph[city1].append((city2, price))

        # minCosts = {}
        # return self.dfs(src, dst, k+1, 0, graph)
        return self.findCheapestPriceDijkstra(n, flights, src, dst, k)
        # return self.bellmanFord(n, flights, src, dst, k)

    def dfs(self, src, dst, stops, currCost, graph):
        if stops < 0:
            return -1
        if src == dst:
            return currCost
        
        minCost = float("inf")
        for nextCity, price in graph[src]:
            if currCost + price >= minCost:
                continue
            res = self.dfs(nextCity, dst, stops - 1, currCost + price, graph)
            if res != -1:
                minCost = min(minCost, res)
        
        return -1 if minCost == float("inf") else minCost
    
    def findCheapestPriceDijkstra(self, n, flights, src, dst, k):
        graph = defaultdict(list)
        for city1, city2, price in flights:
            graph[city1].append((city2, price))
        
        h = [(0, src, k+1)] # (cost, node/city, max stops left)
        visited = [0] * n # stores how many stopsleft last visit
        while h:
            cost, city, stopsLeft = heappop(h)
            if city == dst:
                return cost

            # we'll always get to a node by its least cost path
            # not necessarily shortest path, since there is a max
            # k stop constraint, it is possible the result path to a
            # certain node is not the least cost path, so we can't
            # just not not visit nodes we visited before,
            # so instead we check if current path that costs more
            # than previously visited path to node also took
            # more steps. if so then no need to visit again
            if visited[city] >= stopsLeft:
                continue
            visited[city] = stopsLeft

            if stopsLeft == 0:
                continue

            for nextCity, price in graph[city]:
                heappush(h, (cost + price, nextCity, stopsLeft-1))

        return -1

    def bellmanFord(self, n, flights, src, dst, k):
        # cost[i] = shortest cost from src to i
        cost = [float("inf") for _ in range(n)]
        cost[src] = 0

        graph = defaultdict(list)
        for city1, city2, price in flights:
            graph[city1].append((city2, price))

        for _ in range(k + 1):
            tmp = cost[:]
            for city1, city2, price in flights:
                # not arrived/visited here yet
                if cost[city1] == float("inf"):
                    continue
                tmp[city2] = min(tmp[city2], cost[city1]+price)
            cost = tmp

        return -1 if cost[dst] == float("inf") else cost[dst]