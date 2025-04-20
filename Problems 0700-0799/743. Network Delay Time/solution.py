class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # make sure all nodes are reachable from node k
        # if reachable, then get node furthest (largest weight) from
        # source node

        # build graph
        # graph = defaultdict(list)
        # for u, v, time in times:
        #     graph[u].append((v, time))
        
        # dist = [float("inf")] * n
        # self.dfs(k, graph, dist, 0)
        # totalTime = max(dist)
        # return -1 if totalTime == float("inf") else totalTime
        return self.networkDelayTimeDijkstra(times, n, k)

    def dfs(self, curr, graph, dist, elapsedTime):
        # visited already
        if elapsedTime >= dist[curr-1]:
            return
        
        dist[curr-1] = elapsedTime
        for node, time in graph[curr]:
            self.dfs(node, graph, dist, elapsedTime + time)

    def networkDelayTimeDijkstra(self, times, n, k):

        # build graph
        graph = defaultdict(list)
        for u, v, time in times:
            graph[u].append((v, time))

        h = [(0, k)]
        dist = [0] + [float("inf")] * n
        dist[k] = 0

        while h:
            currTime, node = heappop(h)
            # already visited, and since we always visit
            # a node by its shortest weighted path, we
            # don't need to worry about the dist recorded
            if currTime > dist[node]:
                continue

            for nextNode, time in graph[node]:
                if currTime+time >= dist[nextNode]:
                    continue
                dist[nextNode] = currTime + time
                heappush(h, (currTime + time, nextNode))

        
        maxDist = max(dist)
        return -1 if maxDist == float("inf") else maxDist
