class Solution:
    # Iterative
    # Time complexity: O(n)
    # Space: O(1)
    def findKthBit(self, n: int, k: int) -> str:
        # Start with length of Sn
        slen = (1 << n) - 1
        invert_count = 0

        # Halving slen each time, so we're at most going through
        # while loop log(2^n - 1) is about n -> O(n)
        while k > 1:
            mid = (slen + 1) // 2
            if k == mid:
                return "1" if invert_count % 2 == 0 else "0"
            
            if k > mid:
                k = slen - k + 1
                invert_count += 1
            
            slen //= 2
        
        return "0" if invert_count % 2 == 0 else "1"

    # # Recursion:
    # # Time complexity O(n)
    # # Space: O(n)
    # def findKthBit(self, n: int, k: int) -> str:
    #     if n == 1:
    #         return "0"

    #     slen = (1 << n) - 1
    #     mid = (slen + 1) // 2

    #     if k == mid:
    #         return "1"
    #     elif k < mid:
    #         return self.findKthBit(n - 1, k)
    #     else:
    #         return "0" if self.findKthBit(n - 1, slen - k + 1) == "1" else "1"