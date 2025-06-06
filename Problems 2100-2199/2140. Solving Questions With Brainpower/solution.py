class Solution:
    def mostPoints(self, questions: List[List[int]]) -> int:
        n = len(questions)
        dp = [0]*(n+1)

        for i in range(n-1, -1, -1):
            solve = questions[i][0]
            if i + questions[i][1] + 1 < n:
                solve += dp[i+questions[i][1]+1]
            dp[i] = max(solve, dp[i+1])
        return dp[0]