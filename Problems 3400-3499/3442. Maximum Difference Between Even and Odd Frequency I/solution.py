class Solution:
    def maxDifference(self, s: str) -> int:
        freq = [0] * 26

        for c in s:
            freq[ord(c) - ord('a')] += 1
        
        n = len(s)
        minEven = n + 1
        maxOdd = 0
        for count in freq:
            if count == 0: continue
            if (count % 2) == 0:
                minEven = min(minEven, count)
            else:
                maxOdd = max(maxOdd, count)
        
        return maxOdd - minEven