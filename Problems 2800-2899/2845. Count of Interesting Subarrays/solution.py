class Solution:
    def countInterestingSubarrays(self, nums: List[int], modulo: int, k: int) -> int:
        freq = defaultdict(int)
        freq[0] = 1
        prefixSum = 0
        res = 0
        for num in nums:
            prefixSum = (prefixSum + (1 if num % modulo == k else 0)) % modulo
            res += freq[(prefixSum-k+modulo)%modulo]
            freq[prefixSum] += 1
        return res