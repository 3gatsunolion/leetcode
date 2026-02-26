class Solution:
    def numSteps(self, s: str) -> int:
        carry = 0
        steps = 0
        for i in range(len(s) - 1, 0, -1):
            steps += 1
            if ord(s[i]) - ord('0') + carry == 1:
                steps += 1
                carry = 1

        # Note: s[0] == '1', so it carry is 1 at the end then we have
        # one more divide by 2 step
        return steps + carry