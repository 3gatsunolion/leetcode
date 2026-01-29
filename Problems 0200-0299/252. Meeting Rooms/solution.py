"""
Definition of Interval:
class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
"""

class Solution:
    def canAttendMeetings(self, intervals: List[Interval]) -> bool:
        # intervals.sort(key=lambda x: x.start)

        # prevEnd = -1
        # for interval in intervals:
        #     if interval.start < prevEnd:
        #         return False
        #     prevEnd = interval.end
        # return True

        intervals.sort(key=lambda x: x.end)

        prevEnd = -1
        for interval in intervals:
            if interval.start < prevEnd:
                return False
            prevEnd = interval.end
        return True