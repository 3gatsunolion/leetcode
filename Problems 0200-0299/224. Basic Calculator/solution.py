class Solution:
    def calculate(self, s: str) -> int:
        stack = []
        # running result in current "level"/context
        res = 0
        curr = 0
        sign = 1
        for c in s:
            if c.isnumeric():
                curr = curr*10 + int(c)
            elif c == "+" or c == "-":
                res += curr * sign
                # Reset
                curr = 0
                sign = 1 if c == "+" else -1
            elif c == "(":
                # Start new "context"
                stack.append(res)
                stack.append(sign)
                # Reset
                sign = 1
                # Reset res as we are in a new context/level
                res = 0
            elif c == ")":
                res += sign * curr
                curr = 0
                res *= stack.pop()
                res += stack.pop()

        if curr != 0:
            res += sign * curr
        return res