class Solution:
    def findTheString(self, lcp: List[List[int]]) -> str:
        # 1. If lcp[i][j] > 0, then word[i] == word[j]
        # 2. If lcp[i][j] == 0, then word[i] != word[j]

        n = len(lcp)
        word = [""] * n
        # Start with "a" since we want lexographically smallest
        # Worst case: 'abcdefg...z'
        c = ord("a")
        for i in range(n):
            if word[i]: continue
            # Not possible
            if c > ord('z'):
                return ""
            word[i] = chr(c)
            for j in range(i + 1, n):
                if lcp[i][j] > 0:
                    word[j] = word[i]
            c += 1

        # Validate the string
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if word[i] != word[j]:
                    # Not possible
                    if lcp[i][j]:
                        return ""
                else:
                    if i == n - 1 or j == n - 1:
                        if lcp[i][j] != 1:
                            return ""
                    else:
                        if lcp[i][j] != lcp[i + 1][j + 1] + 1:
                            return ""

        return "".join(word)
