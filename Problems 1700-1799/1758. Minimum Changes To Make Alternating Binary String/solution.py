class Solution:
    def minOperations(self, s: str) -> int:
        # # Number of minimum changes needed to end in zero
        # zero = 0
        # # Number of minimum changes needed to end in one
        # one = 0

        # for c in s:
        #     if c == '0':
        #         zero, one = one, zero + 1
        #     else:
        #         zero, one = 1 + one, zero
        
        # return min(zero, one)

        # We want either 0101010 or 10101010
        # Calculate changes needed to make 0101010
        changes = 0
        for i, c in enumerate(s):
            if c != ("0" if i % 2 == 0 else "1"):
                changes += 1
        
        # len(changes) - changes is for 10101010...
        return min(changes, len(s) - changes)