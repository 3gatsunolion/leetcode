class Solution:
    memo = {}
    def soupServings(self, n: int) -> float:
        # No need to calculate after 4800. Because, when n=4801,
        # the probability is 0.999995382315≈1.0
        # Thus, we can output 1.0 for any n>4800.
        # "Note that we do not have the operation where all 100 ml's of soup B are used first. "
        # It's obvious that A is easier to be empty than B.
        # And when N gets bigger, we have less chance to run out of B first.
        # So as N increases, our result increases and it gets closer to 100 percent = 1.
        if n > 4800:
            return 1

        def dp(a, b):
            if (a, b) in self.memo:
                return self.memo[(a, b)]
            if a <= 0 and b <= 0:
                # Tie
                return 0.5
            if a <= 0:
                return 1
            if b <= 0:
                return 0
            self.memo[(a, b)] = 0.25 * (
                dp(a - 4, b) + 
                dp(a - 3, b - 1) + 
                dp(a - 2, b - 2) + 
                dp(a - 1, b - 3)
            )
            return self.memo[(a, b)]

        # Convert to units of 25 ml 
        n = math.ceil(n / 25.0)
        return dp(n, n)