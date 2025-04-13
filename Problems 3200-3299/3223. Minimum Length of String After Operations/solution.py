class Solution:
    def minimumLength(self, s: str) -> int:
        # basically delete every character until
        # there's one left (if odd number) or two left
        # (if even number of occurrences)
        freq = [0]*26
        for c in s:
            freq[ord(c)-ord('a')] += 1
        res = 0
        for count in freq:
            if count == 0:
                continue
            
            res += 2 if count % 2 == 0 else 1
        
        return res
