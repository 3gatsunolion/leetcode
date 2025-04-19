class Solution:
    def minEnd(self, n: int, x: int) -> int:
        # since we're & ing, all 1 bits in x must be
        # present in all numbers in nums, so we fill
        # in zero elements incrementally, basically interleaving
        # n - 1 with x (filling 0 to n - 1 set bits
        # in x's unset bits). n - 1 because first number is x
        # therefore, nums[n-1] = x interleaved with n - 1

        # bitX: use this to find next unset bit in x for us to set
        # bitN: use this to find next set bit in n - 1
        bitX, bitN = 1, 1


        while bitN < n:
            # The next zero we can fill
            if x & bitX == 0:
                # if this next bit in n - 1 is set, then
                # we set x's vacant spot
                if (n - 1) & bitN != 0:
                    x += bitX
                bitN <<= 1
            bitX <<= 1
        
        return x