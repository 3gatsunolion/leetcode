class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:
        numUnique = len(set(nums))
        # sliding window
        left = 0
        freq = defaultdict(int)
        res = 0
        n = len(nums)
        for right in range(n):
            freq[nums[right]] += 1
            while len(freq) == numUnique:
                # Since we know from here on out, len(freq)
                # cannot be > numUnique, so we add n - right
                # to calculate number of complete subarrays starting
                # at index left
                res += n - right
                freq[nums[left]] -= 1
                if freq[nums[left]] == 0:
                    del freq[nums[left]]
                left += 1
            # res += left
        
        return res