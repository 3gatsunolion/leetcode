class Solution:
    def romanToInt(self, s: str) -> int:
        symbolToVal = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000
        }

        total = 0
        n = len(s)
        for i, c in enumerate(s):
            if i < n-1 and symbolToVal[s[i]] < symbolToVal[s[i+1]]:
                total -= symbolToVal[c]
            else:
                total += symbolToVal[c]
        
        return total