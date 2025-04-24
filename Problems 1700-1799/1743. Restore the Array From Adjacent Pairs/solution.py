class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        graph = defaultdict(list)
        for u, v in adjacentPairs:
            graph[u].append(v)
            graph[v].append(u)

        # find nodes with only 1 neighbor, that will be
        # either head or tail (both will work since
        # order of pairs doesn't matter)
        res = []
        for node, neighbors in graph.items():
            if len(neighbors) == 1:
                res = [node, neighbors[0]]
                break

        # length of res should be len(adjacentPairs) + 1
        while len(res) < len(adjacentPairs) + 1:
            prev = res[-2]
            curr = res[-1]
            if graph[curr][0] == prev:
                res.append(graph[curr][1])
            else:
                res.append(graph[curr][0])
        return res