class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        # num left parentheses must always be greater
        # than number of right parentheses.
        # Space complexity: O(2*n)=O(n) --> size of string, since
        # height of recursion call stack will be 2*n
        # Time complexity: O(2^n)?
        res = []
        self.backtrack(0, 0, n, "", res)
        return res

    def backtrack(self, numLeft, numRight, n, curr, res):
        if numLeft == numRight and numLeft == n:
            res.append(curr)
            return
        
        if numLeft < n:
            self.backtrack(numLeft + 1, numRight, n, curr + '(', res)
        if numRight < n and numLeft > numRight:
            self.backtrack(numLeft, numRight + 1, n, curr + ')', res)
        