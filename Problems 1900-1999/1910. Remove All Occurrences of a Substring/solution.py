class Solution:
    def removeOccurrences(self, s: str, part: str) -> str:
        stack = []
        partLen = len(part)
        for i, c in enumerate(s):
            stack.append(c)
            if i >= partLen - 1 and "".join(stack[-partLen:]) == part:
                for _ in range(partLen): stack.pop()

        return "".join(stack)