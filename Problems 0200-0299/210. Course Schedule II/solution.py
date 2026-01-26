class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        def hasCycle(node, graph, visited, res):
            if visited[node] == -1:
                return True
            if visited[node] == 1:
                return False
            visited[node] = -1
            for adj in graph[node]:
                if hasCycle(adj, graph, visited, res):
                    return True
            visited[node] = 1
            res.append(node)
            return False

        graph = [[] for _ in range(numCourses)]
        for a, b in prerequisites:
            graph[b].append(a)

        visited = [0] * numCourses
        res = []
        for course in range(numCourses):
            if hasCycle(course, graph, visited, res):
                return []
        res.reverse()
        return res

    def findOrderBFS(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        graph = [[] for _ in range(numCourses)]
        indegrees = [0]*numCourses
        for a, b in prerequisites:
            graph[b].append(a)
            indegrees[a] += 1

        q = deque()
        for i, indegree in enumerate(indegrees):
            if indegree == 0:
                q.append(i)

        res = []
        while q:
            course = q.popleft()
            res.append(course)

            for nextCourse in graph[course]:
                indegrees[nextCourse] -= 1
                if indegrees[nextCourse] == 0:
                    q.append(nextCourse)
        
        if len(res) == numCourses:
            return res
        return []