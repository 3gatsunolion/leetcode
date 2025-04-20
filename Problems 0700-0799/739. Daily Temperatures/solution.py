class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        # # start from end, keep a monotically strictly decreasing
        # # stack of temperatures, we pop off temps that are smaller
        # # than current temp because any temp that comes before
        # # current temp won't have those temperatures as their next
        # # warmer temperature, because current temp are warmer
        # # than those.
        # n = len(temperatures)
        # stack = []
        # res = [0] * n
        # for i in range(n-1, -1, -1):
        #     temp = temperatures[i]
        #     while stack and temperatures[stack[-1]] <= temp:
        #         stack.pop()
            
        #     if len(stack) > 0:
        #         warmerDay = stack[-1]
        #         res[i] = warmerDay - i
        #     stack.append(i)
        # return res

        return self.dailyTemperatures2(temperatures)

    def dailyTemperatures2(self, temperatures: List[int]) -> List[int]:
        n = len(temperatures)
        stack = []
        res = [0] * n
        for i, temp in enumerate(temperatures):
            # keep monoticlaly decreasing stack, if current
            # temp is greater than past days, then current
            # day is the next warmer day for those
            while stack and temperatures[stack[-1]] < temp:
                colderDay = stack.pop()
                res[colderDay] = i - colderDay
            stack.append(i)
        return res