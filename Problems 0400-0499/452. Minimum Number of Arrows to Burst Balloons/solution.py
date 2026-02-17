class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        n = len(points)
        if n == 0:
            return 0
        # Sort by end so we can hit at the end to maximize
        # number of balloons overlapping
        points.sort(key=lambda b: b[1])

        arrow = points[0][1]
        res = 1
        for i in range(1, n):
            start, end = points[i]
            if start > arrow:
                # have to shoot new arrow
                arrow = end
                res += 1
        return res