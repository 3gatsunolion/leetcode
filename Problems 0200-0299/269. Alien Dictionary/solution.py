class Solution:
    # 1. DFS Topological Sort
    # Time: O(N + V + E) -> N is sum of length of all strings
    # Space: O(V + E)
    # def foreignDictionary(self, words: List[str]) -> str:
    #     def hasCycle(node, graph, visited, order):
    #         key = ord(node) - ord('a')
    #         if visited[key] == -1:
    #             return True
            
    #         if visited[key] == 1:
    #             return False
            
    #         visited[key] = -1

    #         for adj in graph[node]:
    #             if hasCycle(adj, graph, visited, order):
    #                 return True
            
    #         visited[key] = 1
    #         order.append(node)
    #         return False

    #     graph = { c : set() for word in words for c in word }

    #     n = len(words)
    #     for i in range(1, n):
    #         word1 = words[i - 1]
    #         word2 = words[i]

    #         minLen = min(len(word1), len(word2))

    #         # CRUCIAL STEP: Otherwise graph won't catch that it's invalid
    #         if len(word1) > len(word2) and word1[:minLen] == word2[:minLen]:
    #             return ""

    #         for j in range(minLen):
    #             if word1[j] != word2[j]:
    #                 graph[word1[j]].add(word2[j])
    #                 break

    #     visited = [0] * 26
    #     order = []
        
    #     for node in graph:
    #         if hasCycle(node, graph, visited, order):
    #             return ""
        
    #     return ''.join(reversed(order))

    # 2. Kahn's Algorithm
    # Time complexity: O(N + V + E)
    # Space complexity: O(V + E)
    def foreignDictionary(self, words: List[str]) -> str:
        graph = { c: set() for word in words for c in word }
        indegrees = { c: 0 for c in graph }
        n = len(words)
        for i in range(1, n):
            word1 = words[i - 1]
            word2 = words[i]

            minLen = min(len(word1), len(word2))

            # CRUCIAL STEP: Otherwise graph won't catch that it's invalid
            if len(word1) > len(word2) and word1[:minLen] == word2[:minLen]:
                return ""

            for j in range(minLen):
                if word1[j] != word2[j]:
                    # CRUCIAL: Don't want to increment indegrees
                    # for duplicate edge
                    if word2[j] not in graph[word1[j]]:
                        graph[word1[j]].add(word2[j])
                        indegrees[word2[j]] += 1
                    break
        
        q = deque([c for c in indegrees if indegrees[c] == 0])
        order = []
        while q:
            node = q.popleft()
            order.append(node)

            for adj in graph[node]:
                indegrees[adj] -= 1
                if indegrees[adj] == 0:
                    q.append(adj)
        
        if len(order) != len(indegrees):
            return ""
        
        return "".join(order)