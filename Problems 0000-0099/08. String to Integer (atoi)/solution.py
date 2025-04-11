import math
MAX_INT = int(math.pow(2, 31) - 1)
MIN_INT = int(math.pow(-2, 31))
class Solution:
    def myAtoi(self, s: str) -> int:
        res = 0
        sign = 1
        i = 0
        n = len(s)
        # Get rid of leading whitespace
        while i < n and s[i] == " ":
            i += 1

        if i < n and (s[i] == "+" or s[i] == "-"):
            if s[i] == "-":
                sign = -1
            i += 1
        
        while i < n and s[i].isdigit():
            if res > MAX_INT // 10 or res == MAX_INT // 10 and int(s[i]) > MAX_INT % 10:
                if sign == 1:
                    return MAX_INT
                else:
                    return MIN_INT

            res = res*10 + int(s[i])
            i += 1

        return res*sign

    def dfa(self, s):
        res, sign, state, i, n = 0, 1, 0, 0, len(s)

        while i < n:
            if state == 0:
                if s[i] == " ":
                    state = 0
                elif s[i] == "-" or s[i] == "+":
                    state = 1
                    if s[i] == "-":
                        sign = -1
                elif s[i].isdigit():
                    state = 2
                    res = res*10 + int(s[i])
                else:
                    return 0
            elif state == 1 or state == 2:
                if s[i].isdigit():
                    state = 2
                    res = res*10 + int(s[i])
                else:
                    if state == 0:
                        return 0
                    else:
                        break
            else:
                return 0
            i += 1

        res *= sign
        res = min(res, MAX_INT)
        res = max(res, MIN_INT)
        return res