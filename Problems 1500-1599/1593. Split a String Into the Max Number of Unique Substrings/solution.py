class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def backtrack(s, start, seen):
            nonlocal count
            if start == len(s):
                count = max(count, len(seen))
                return

            # If we cannot split any more than current max, return
            if len(seen) + len(s) - start <= count:
                return

            for end in range(start, len(s)+1):
                substring = s[start:end + 1]
                if substring in seen:
                    continue
                seen.add(substring)
                backtrack(s, end + 1, seen)
                seen.remove(substring)
            return count

        count = 0
        backtrack(s, 0, set())
        return count