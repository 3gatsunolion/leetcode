class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        s = s[1:-1]
        n = len(s)
        res = []
        for i in range(1, n):
            left = self.getPossibleCoords(s[:i])
            right = self.getPossibleCoords(s[i:])

            for l in left:
                for r in right:
                    res.append(f"({l}, {r})")
        return res
        
    def getPossibleCoords(self, s):
        # 0 -> 0
        # 0XXXXX -> 0.XXXXX
        # 0xxxx0 -> nothing
        # XXXXX0 -> [XXXXX0]
        # XXXXXX -> [XXXXXX, X.XXXXX, XX.XXXX, ...]
        n = len(s)
        if n == 0 or (n > 1 and s[0] == s[-1] == '0'): return []
        if n == 1 or s[-1] == '0': return [s]
        if n > 1 and s[0] == '0': return ['0.' + s[1:]]
        res = [s]
        for i in range(1, n):
            res.append(s[:i] + "." + s[i:])

        return res