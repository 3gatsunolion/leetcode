class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        # n = len(events)
        # events.sort(key=lambda x: x[1])
        # ends = [end for _, end, _ in events]
        # dp = [[0] * (k + 1) for _ in range(n + 1)]

        # for i in range(1, n + 1):
        #     prev = bisect.bisect_right(ends, events[i-1][0] - 1)
        #     for count in range(1, k + 1):
        #         dp[i][count] = max(dp[i-1][count], events[i-1][2] + dp[prev][count-1])
    
        # return dp[n][k]

        n = len(events)
        events.sort(key=lambda x: x[0])
        starts = [start for start, _, _ in events]
        dp = [[-1] * (k + 1) for _ in range(n)]

        def solve(pos, k):
            if pos == n or k == 0:
                return 0
            
            if dp[pos][k] != -1:
                return dp[pos][k]

            # nextPos = bisect.bisect_right(events, events[pos][1], lo=pos + 1, key=lambda x: x[0])
            nextPos = bisect_right(starts, events[pos][1])
            
            dp[pos][k] = max(solve(pos + 1, k), events[pos][2] + solve(nextPos, k - 1))
            return dp[pos][k]
        
        return solve(0, k)