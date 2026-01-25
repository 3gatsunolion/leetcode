class Solution:
    def countTriples(self, n: int) -> int:
        issquare = [False] * (n * n + 1)

        for i in range(1, n + 1):
            issquare[i * i] = True

        res = 0
        for i in range(1, n + 1):
            for j in range(i, n + 1):
                square = i * i + j * j
                if square > n * n: break
                res += issquare[square] * 2
        
        return res