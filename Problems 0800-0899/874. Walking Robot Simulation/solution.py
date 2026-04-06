class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        DIRS = [[0, 1], [1, 0], [0, -1], [-1, 0]]

        obstacles = { (o[0], o[1]) for o in obstacles }

        x, y = 0, 0
        d = 0
        maxDist = 0
        for command in commands:
            if command == -2:
                d = (d + 3) % 4
            elif command == -1:
                d = (d + 1) % 4
            else:
                for step in range(command):
                    nx = x + DIRS[d][0]
                    ny = y + DIRS[d][1]

                    # Can't keep going
                    if (nx, ny) in obstacles:
                        break
                    
                    x = nx
                    y = ny
                    maxDist = max(maxDist, x * x + y * y)

        return maxDist
            