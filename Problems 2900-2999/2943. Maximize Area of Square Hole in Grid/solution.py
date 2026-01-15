class Solution:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        def getMaxGap(bars):
            if len(bars) == 0:
                return 1
            # bars = sorted(bars)
            bars.sort()
            # Remove first bar gives us gap 2
            currGap = 2
            res = 2
            for i in range(1, len(bars)):
                if bars[i] == bars[i - 1] + 1:
                    currGap += 1
                    res = max(currGap, res)
                else:
                    # Reset since it's not consecutive bars
                    currGap = 2
            return res
        
        gap = min(getMaxGap(hBars), getMaxGap(vBars))
        return gap * gap