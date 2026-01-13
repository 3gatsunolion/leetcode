class Solution:
    def maxDifference(self, s: str, k: int) -> int:
        def getState(a, b):
            # Status:
            # 0 = even, 1 = odd
            return ((a & 1) << 1) | (b & 1)

        # Since there's only 5 unique characters, we can iterate
        # over different unique pairs {a, b} such that
        # freq[a]-freq[b] satisfies conditions in basically O(1) time
        
        # Total 20 pairs (5*4)
        # Prefix sums:
        # freq[a] from s[i:j] = count(a, j) - count(a, i-1)
        # freq[b] from s[i:j] = count(b, j) - count(b, i-1)
        # We want to maximize freq[a] - freq[b]:
        # count(a, j) - count(a, i-1) - (count(b, j) - count(b, i-1))
        # -> For a given substring ending at j, we want to minimize:
        # count(a, i-1) - count(b, i-1)
        n = len(s)
        res = float("-inf")
        for a in "01234":
            for b in "01234":
                if a == b: continue

                # Want: 10 -> odd a, even b
                # 10 - 00 = 10
                # 11 - 01 = 10
                # 01 - 11 = 10
                # 00 - 10 = 10
                minDiff = [float("inf")]*4
                freqA, freqB = 0, 0
                leftA, leftB = 0, 0

                left = -1

                for right in range(n):
                    freqA += 1 if s[right] == a else 0
                    freqB += 1 if s[right] == b else 0

                    bDiff = freqB - leftB
                    # Don't want to count when freqB = 0 and freqB = 1
                    # since those are invalid frequency for character b
                    # It needs to be at least 2
                    while (right - left) >= k and bDiff >= 2:
                        leftState = getState(leftA, leftB)
                        minDiff[leftState] = min(minDiff[leftState], leftA - leftB)
                        left += 1
                        leftA += 1 if s[left] == a else 0
                        leftB += 1 if s[left] == b else 0

                    need = getState(freqA, freqB) ^ 0b10
                    if minDiff[need] != float("inf"):
                        res = max(res, freqA - freqB - minDiff[need])
        
        return res