class Solution:
    # Time: O(n * 2^n)
    # Space: O(n * 2^n)
    # def getHappyString(self, n: int, k: int) -> str:
    #     total = 3 * (1 << (n - 1))

    #     if k > total:
    #         return ""

    #     nextLetters = {"a": "bc", "b": "ac", "c": "ab"}
    #     q = deque(["a", "b", "c"])
    #     i = 0
    #     while len(q[0]) != n:
    #         curr = q.popleft()
    #         for c in nextLetters[curr[-1]]:
    #             q.append(curr + c)
        
    #     return q[k - 1] if len(q) >= k else ""

    # Time: O(n)
    # Space: O(1)
    def getHappyString(self, n: int, k: int) -> str:
        # Total happy strings: 3 * 2^(n - 1)
        total = 3 * (1 << (n - 1))

        if k > total:
            return ""
        
        res = ""
        prev = ""
        for i in range(n):
            count = 1 << (n - 1 - i)
            for c in "abc":
                if prev == c:
                    continue
                if k <= count:
                    # This is the character at this position!!
                    res += c
                    prev = c
                    break
                k -= count
        
        return res