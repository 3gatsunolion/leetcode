class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start_node: int, end_node: int) -> float:
        graph = defaultdict(list)
        for i, (a, b) in enumerate(edges):
            graph[a].append((b, succProb[i]))
            graph[b].append((a, succProb[i]))
        
        h = [(-1, start_node)]
        p = [0] * n
        p[start_node] = 1
        while h:
            prob, node = heappop(h)
            if node == end_node:
                return -prob

            # in cases where this was added to heap first,
            # but later a better path to node was visited
            # first (can be avoided if we implement edge
            # relaxation, but emmmmmmm)
            if -prob < p[node]:
                continue
            
            for nextNode, nextProb in graph[node]:
                newProb = -prob*nextProb
                if newProb <= p[nextNode]:
                    continue
                p[nextNode] = newProb
                heappush(h, (-newProb, nextNode))

        return 0
        