class MedianFinder:

    def __init__(self):
        self.left = []
        self.right = []

    def addNum(self, num: int) -> None:
        # If odd number of nums, one will
        # have one more. We'll choose right
        if len(self.left) == len(self.right):
            heappush(self.left, -num)
            mid = -heappop(self.left)
            heappush(self.right, mid)
        else:
            heappush(self.right, num)
            mid = heappop(self.right)
            heappush(self.left, -mid)

    def findMedian(self) -> float:
        n = len(self.left) + len(self.right)
        if n % 2 == 0:
            l = -self.left[0]
            r = self.right[0]
            return (l + r) / 2
        else:
            return self.right[0]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()

# Follow up:
# 1. If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
# Keep array length 101 to keep track of num frequency.
# Now we just keep track of num frequency and sum of all nums added?
# So we need to find minimum i such that 2*sum(numFreq[:i+1]) >= sum(numFreq) to get median
# 2. If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
# Keep track of freq of numbers outside range and follow same steps as 1.
