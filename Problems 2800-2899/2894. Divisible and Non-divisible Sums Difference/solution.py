class Solution:
    def differenceOfSums(self, n: int, m: int) -> int:
        # m + 2*m + 3*m + ... + k*m
        # = (1 + 2 + 3 + ... + k)*m
        # = ((k+1)*k/2)*m
        total = (n+1)*n//2
        k = n // m
        num2 = ((k+1)*k//2)*m
        return total - 2*num2