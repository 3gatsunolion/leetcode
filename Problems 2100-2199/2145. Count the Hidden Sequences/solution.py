class Solution:
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        n = len(differences)
        # Start with arbitrary x
        # x, x+differences[0], x+differences[0]+differences[1],
        # x+differences[0]+differences[1]+differences[2]...
        # find min and max of these
        delta = 0
        minDelta = 0
        maxDelta = 0
        for diff in differences:
            delta += diff
            minDelta = min(minDelta, delta)
            maxDelta = max(maxDelta, delta)
        
        # Now we want to find out:
        # x + minDelta - x + maxDelta -> how many will stay in
        # range
        # res = 0
        # for cand in range(lower, upper+1):
        #     if (cand + minDelta) >= lower and (cand + maxDelta) <= upper:
        #         res += 1
        
        # return res

        return max(0, (upper - lower) - (maxDelta - minDelta) + 1)