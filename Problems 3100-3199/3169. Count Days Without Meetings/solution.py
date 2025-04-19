class Solution:
    def countDays(self, days: int, meetings: List[List[int]]) -> int:
        meetings.sort()

        prevEnd = 0
        daysOff = 0
        for start, end in meetings:
            daysOff += max(0, start - prevEnd - 1)
            prevEnd = max(prevEnd, end)
            if prevEnd >= days:
                break
        return daysOff + max(0, days - prevEnd)