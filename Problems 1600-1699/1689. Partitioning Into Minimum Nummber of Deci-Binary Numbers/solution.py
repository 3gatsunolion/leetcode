class Solution:
    def minPartitions(self, n: str) -> int:
        chars = set(n)
        return int(max(chars))