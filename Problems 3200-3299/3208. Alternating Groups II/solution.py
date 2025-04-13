class Solution:
    def numberOfAlternatingGroups(self, colors: List[int], k: int) -> int:
        n = len(colors)
        count = 0
        res = 0
        for i in range(0, n + k - 1):
            if i == 0 or colors[i%n] != colors[(i-1) % n]:
                count += 1
            else:
                count = 1
            
            if count >= k:
                res += 1
        return res