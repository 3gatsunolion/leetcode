mod = 10 ** 9 + 7
class Solution:
    def countGoodArrays(self, n: int, m: int, k: int) -> int:
        # m * pow(m - 1, n - k - 1, mod): calculate number of non-identical
        # pairs
        # comb(n - 1, k): calculate number of identical pairs ->
        # from a sequence of length n, pick k positions to be the matching
        # adjacent elements to element before. can't choose first element
        # since it's first element and can't have same element before it
        # so we have: (n-1)Ck
        return m * pow(m - 1, n - k - 1, mod) * comb(n - 1, k) % mod