class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        # 1. all odd numbers
        # 2. all even numbers
        # 3. alternating parity sequence
        same = [0]*2 # same parity
        diff = [0]*2 # alternating 1, 0 or 0, 1 was last two numbers
        for num in nums:
            same[num % 2] += 1
            diff[num % 2] = diff[(num + 1) % 2] + 1
        
        return max(max(same[0], same[1]), max(diff[0], diff[1]))