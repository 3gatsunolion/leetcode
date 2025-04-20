class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        # if s == goal:
        #     return True
        # rotate = s
        # for i in range(len(s) - 1):
        #     rotate = rotate[1:] + rotate[0]
        #     if rotate == goal:
        #         return True
        # return False

        return len(s) == len(goal) and goal in (s+s)