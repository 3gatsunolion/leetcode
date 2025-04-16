class BIT:
    def __init__(self, n):
        self.n = n
        self.sums = [0]*(n+1)
    
    def update(self, i, delta):
        while i <= self.n:
            self.sums[i] += delta
            i += i & -i
    
    def query(self, i):
        total = 0
        while i > 0:
            total += self.sums[i]
            i -= i & -i
        return total

    def sumRange(self, l, r):
        return self.query(r) - self.query(l-1)

class Solution:
    def findMinimumTime(self, tasks: List[List[int]]) -> int:
        tasks.sort(key=lambda x: x[1])

        bit = BIT(tasks[-1][1]) # max end time
        minTime = 0
        for start, end, dur in tasks:
            timeUsed = bit.sumRange(start, end)
            timeLeft = dur - timeUsed

            for i in range(end, start-1, -1):
                if timeLeft <= 0:
                    break
                
                # since prefix sum is same as prefix index,
                # we know there's an empty slot here
                if bit.query(i) == bit.query(i-1):
                    bit.update(i, 1)
                    timeLeft -= 1
                    minTime += 1

        return minTime

    def findMinimumTimeGreedy(self, tasks):
        tasks.sort(key=lambda x: x[1])

        timeCovered = set()
        minTime = 0
        for start, end, dur in tasks:
            usedTime = 0
            for i in range(start, end+1):
                if i in timeCovered:
                    usedTime += 1

            timeLeft = max(dur - usedTime, 0)
            for i in range(end, start - 1, -1):
                if timeLeft <= 0:
                    break
                if i not in timeCovered:
                    timeCovered.add(i)
                    minTime += 1
                    timeLeft -= 1

        return minTime