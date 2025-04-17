class Solution:
    def countPairs(self, nums: List[int], k: int) -> int:
        n = len(nums)
        seenSoFar = defaultdict(list)
        res = 0
        for i in range(n):
            for j in seenSoFar[nums[i]]:
                if i*j % k == 0:
                    res += 1
            seenSoFar[nums[i]].append(i)
        return res

        # # Brute force
        # n = len(nums)
        # res = 0
        # for i in range(n):
        #     for j in range(i+1, n):
        #         if nums[i] == nums[j] and i*j % k == 0:
        #             res += 1
        # return res