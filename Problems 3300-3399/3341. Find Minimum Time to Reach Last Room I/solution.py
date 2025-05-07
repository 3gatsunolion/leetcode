DIRS = [0, -1, 0, 1, 0]
class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        n = len(moveTime)
        m = len(moveTime[0])
        minTime = [[float("inf")]*m for _ in range(n)]
        minTime[0][0] = 0

        minHeap = [[0, 0, 0]] # time, x, y
        while minHeap:
            time, x, y = heappop(minHeap)

            # skip since this is stale
            if minTime[x][y] < time:
                continue

            if x == n-1 and y == m-1:
                return time
            
            for i in range(4):
                dx = x + DIRS[i]
                dy = y + DIRS[i+1]

                if dx < 0 or dy < 0 or dx == n or dy == m:
                    continue
                
                t = time + 1
                if moveTime[dx][dy] > time:
                    t = moveTime[dx][dy] + 1

                if t < minTime[dx][dy]:
                    minTime[dx][dy] = t
                    heappush(minHeap, [t, dx, dy])

        return -1