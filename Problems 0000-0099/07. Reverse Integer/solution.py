import math
MAX_INT = math.pow(2, 31) - 1
MIN_INT = math.pow(-2, 31)

class Solution:
    def reverse(self, x: int) -> int:
        rev = 0
        while x:
            # python's mod is weird, i.e. -12 % 10 = 8, and not
            # -2, or -5 % 10 = 5 not -5.
            digit = x % 10 if x >= 0 else (abs(x) % 10)*-1
            if rev > MAX_INT // 10 or (rev == MAX_INT // 10 and digit > MAX_INT % 10):
                return 0
            if rev < math.ceil(MIN_INT / 10) or (rev == math.ceil(MIN_INT / 10) and digit < -8):
                return 0
            
            rev = rev*10 + digit
            x = x // 10 if x >= 0 else math.ceil(x / 10)
        return rev
        