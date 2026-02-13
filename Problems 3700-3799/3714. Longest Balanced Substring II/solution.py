class Solution:
    def longestBalanced(self, s: str) -> int:
        if not s:
            return 0

        # Case 1: longest substring with only 1 letter (i.e. 'aa', 'bbbb')
        longest = run = 1
        curr = s[0]
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                run += 1
                longest = max(longest, run)
            else:
                run = 1
                curr = s[i]

        # Case 2: Longest substring with only 2 distinct characters
        def longest_two(s, x, y):
            first_occur = {0: -1}
            balance = 0
            res = 0
            for r, c in enumerate(s):
                if c != x and c != y:
                    balance = 0
                    first_occur = {0: r}
                    continue

                if c == x:
                    balance += 1
                else:
                    balance -= 1

                if balance in first_occur:
                    res = max(res, r - first_occur[balance])
                else:
                    first_occur[balance] = r
            return res

        res = max(longest, longest_two(s, 'a', 'b'))
        res = max(res, max(longest_two(s, 'a', 'c'),
                           longest_two(s, 'b', 'c')))
        

        # Case 3: All 3 'a', 'b', 'c' have same frequency
        # for substring s[j:i]:
        # Need: ai - aj = bi - bj = ci - cj
        # -> ai - bi = aj - bj AND ai - ci = aj - cj
        # So we keep track of (a - b, a - c) values
        first_occur = {(0, 0): -1}
        ab = 0
        ac = 0
        for r, c in enumerate(s):
            if c == 'a':
                ab += 1
                ac += 1
            elif c == 'b':
                ab -= 1
            elif c == 'c':
                ac -= 1

            key = (ab, ac)
            if key in first_occur:
                res = max(res, r - first_occur[key])
            else:
                first_occur[key] = r
        return res