class Solution:
    def findMaximizedCapital(self, k: int, w: int, profits: List[int], capital: List[int]) -> int:
        # heap that contains all projects with enough capital
        # to start
        n = len(profits)
        projects = sorted(zip(profits, capital), key=lambda x: x[1])
        
        i = 0
        capital = w
        projectsToStart = []
        for _ in range(k):
            while i < n and projects[i][1] <= capital:
                heappush(projectsToStart, -projects[i][0])
                i += 1
            
            if not projectsToStart:
                break
            
            profit = -heappop(projectsToStart)
            capital += profit
        
        return capital