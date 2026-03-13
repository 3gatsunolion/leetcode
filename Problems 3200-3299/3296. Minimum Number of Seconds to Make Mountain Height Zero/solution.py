class Solution:
    def minNumberOfSeconds(self, mountainHeight: int, workerTimes: List[int]) -> int:
        # To decrease the mountain's height by x, it takes workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x seconds
        # -> Rewrite as:
        # workerTimes[i] * (1 + 2 + ... + x)
        # = workerTimes[i] * ((x + 1) * x / 2)

        def canReduceInTime(maxTime):
            heightLeft = mountainHeight
            for t in workerTimes:
                # Want: t * (x + 1) * x / 2 <= maxTime
                # -> t*x^2 + t*x - (2 * maxTime) = 0
                # -> (-b + sqrt(b^2 - 4ac)) / 2a (Quadratic formula!)
                discriminant = 1 + 8 * maxTime / t
                x = int((-1 + sqrt(discriminant)) // 2)
                heightLeft -= x

                if heightLeft <= 0: return True
            return False

        lo = 1
        # hi = min(workerTimes) * (mountainHeight + 1) * mountainHeight // 2
        h = ceil(mountainHeight / len(workerTimes))
        hi = max(workerTimes) * (h + 1) * h // 2

        while lo < hi:
            mid = (lo + hi) // 2
            if canReduceInTime(mid):
                hi = mid
            else:
                lo = mid + 1
        
        return lo

    # def minNumberOfSeconds(self, mountainHeight: int, workerTimes: List[int]) -> int:
    #     # (seconds_that_have_past, workerTime, height_reduced_by_worker_i)
    #     h = [(t, t, 1) for i, t in enumerate(workerTimes)]
    #     heapify(h)
    #     while mountainHeight > 1:
    #         s, wt, x = heappop(h)
    #         heappush(h, (s + wt * (x + 1), wt, x + 1))
    #         mountainHeight -= 1

    #     return heappop(h)[0] 