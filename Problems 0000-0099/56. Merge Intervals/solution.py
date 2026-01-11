class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        n = len(intervals)
        if n == 0: return []
        intervals.sort(key=lambda x: x[0])
        res = [intervals[0]]
        for i in range(1, n):
            start, end = intervals[i]
            if start > res[-1][1]:
                res.append([start, end])
            else:
                res[-1][1] = max(res[-1][1], end)
        return res