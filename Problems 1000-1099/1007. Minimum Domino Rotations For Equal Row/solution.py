class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        # Find if there is number that each domino has either top or
        # bottom (could be both if all dominoes have same two numbers)
        # Then minimum rotations would be the min dominoes that have
        # the number on top or bottom. For example, if 4 out of 6 dominoes
        # have 2 on top and other 2 dominoes have 2 on bottom, we rotate
        # the 2 dominoes that have 2 on bottom.

        n = len(tops)
        if n == 0: return 0
        
        # Numbers are from 1 to 6
        topCount = [0] * 7
        bottomCount = [0] * 7
        same = [0] * 7
        for i in range(n):
            topCount[tops[i]] += 1
            bottomCount[bottoms[i]] += 1
            if tops[i] == bottoms[i]:
                same[tops[i]] += 1

        for num in range(1, 7):
            if topCount[num] + bottomCount[num] - same[num] == n:
                return n - max(topCount[num], bottomCount[num])
        return -1
        
            