class Solution:
    def takeCharacters(self, s: str, k: int) -> int:
        freq = [0] * 3
        for c in s:
            freq[ord(c)-ord('a')] += 1
        
        if any(count < k for count in freq):
            return -1

        # instead of trying out diff combos of left and right,
        # just find max we can take out from the middle
        l = r = 0
        n = len(s)
        for r in range(n):
            freq[ord(s[r])-ord('a')] -= 1

            # no longer valid, bring left counter forward
            # don't have to while loop, since we just need
            # to keep the widest window
            # we want to find widest window where freq[0] >= k
            # freq[1] >= k, and freq[2] >= k, because this
            # means there's enough characters on the ends
            # to take
            if freq[0] < k or freq[1] < k or freq[2] < k:
                freq[ord(s[l])-ord('a')] += 1
                l += 1

        return n - (r - l + 1)

        # required = 3
        # currCount = defaultdict(int)
        # n = len(s)
        # right = n
        # for i in range(n-1, -1, -1):
        #     currCount[s[i]] += 1
        #     if currCount[s[i]] == k:
        #         required -= 1 
        #     if required == 0:
        #         right = i
        #         break

        # res = n-right
        # for i in range(n):
        #     currCount[s[i]] += 1

        #     while right < n and currCount[s[right]] > k:
        #         currCount[s[right]] -= 1 
        #         right += 1

        #     res = min(res, i + 1 + n - right)

        #     if right == n:
        #         break

        # return res