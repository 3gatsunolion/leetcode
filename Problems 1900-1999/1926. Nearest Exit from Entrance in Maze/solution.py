class Solution:
    def nearestExit(self, maze: List[List[str]], entrance: List[int]) -> int:
        m, n = len(maze), len(maze[0])
        q = deque([entrance])

        dirs = [0, -1, 0, 1, 0]
        maze[entrance[0]][entrance[1]] = '+'
        # visited = [[False]*n for _ in range(m)]
        # visited[entrance[0]][entrance[1]] = True
        steps = 0
        while q:
            qLen = len(q)
            for _ in range(qLen):
                x, y = q.popleft()
            
                for i in range(4):
                    dx, dy = x + dirs[i], y + dirs[i+1]

                    if dx < 0 or dy < 0 or dx >= m or dy >= n \
                        or maze[dx][dy] == '+':
                        continue
                    maze[dx][dy] = '+'
                    # visited[dx][dy] = True
                    if dx == 0 or dy == 0 or dx == m-1 or dy == n-1:
                        return steps + 1
                    q.append([dx, dy])
            steps += 1
        return -1

                
