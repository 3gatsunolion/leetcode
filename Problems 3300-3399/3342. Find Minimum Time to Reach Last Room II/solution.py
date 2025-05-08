DIRS = [0, -1, 0, 1, 0]
class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        n, m = len(moveTime), len(moveTime[0])

        minTime = [[float("inf")]*m for _ in range(n)]
        minTime[0][0] = 0

        minHeap = [[0, 0, 0, 1]]

        while minHeap:
            time, x, y, timeToMove = heappop(minHeap)

            if minTime[x][y] < time:
                continue

            if x == n-1 and y == m-1:
                return time

            for i in range(4):
                dx = x + DIRS[i]
                dy = y + DIRS[i+1]

                if dx < 0 or dy < 0 or dx == n or dy == m:
                    continue
                
                waitTime = max(0, moveTime[dx][dy] - time)
                newTime = time + waitTime + timeToMove
                if newTime < minTime[dx][dy]:
                    minTime[dx][dy] = newTime
                    heappush(minHeap, [newTime, dx, dy, 3-timeToMove])
        
        return -1