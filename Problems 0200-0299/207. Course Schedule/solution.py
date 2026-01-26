class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        graph = [[] for _ in range(numCourses)]
        indegrees = [0] * numCourses
        
        for a, b in prerequisites:
            graph[b].append(a)
            indegrees[a] += 1

        q = deque()
        for course in range(numCourses):
            if indegrees[course] == 0:
                q.append(course)

        count = 0
        while q:
            course = q.popleft()
            count += 1

            for nextCourse in graph[course]:
                indegrees[nextCourse] -= 1
                if indegrees[nextCourse] == 0:
                    q.append(nextCourse)
        
        return count == numCourses