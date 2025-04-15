class Solution:
    def minimumTime(self, time: List[int], totalTrips: int) -> int:
        # best case: it takes 1 time
        # worst case: we make shortest bus trip do it totalTrips
        # so it takes min(time)*totalTrips total time
        lo, hi = 1, totalTrips * min(time)

        while lo < hi:
            mid = lo + (hi - lo) // 2
            # find number of trips it takes in mid time
            if self.numTrips(time, mid) < totalTrips:
                lo = mid + 1
            else:
                hi = mid
        return lo

    def numTrips(self, busTimes, time):
        total = 0
        for busTime in busTimes:
            total += time // busTime
        return total