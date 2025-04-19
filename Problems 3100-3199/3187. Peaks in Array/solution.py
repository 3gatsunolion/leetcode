class BIT:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (n + 1)

    def update(self, i, delta):
        i += 1
        while i <= self.n:
            self.tree[i] += delta
            i += i & -i

    def sumRange(self, left, right):
        if left > right:
            return 0
        return self.prefixSum(right+1) - self.prefixSum(left)
    
    def prefixSum(self, i):
        total = 0
        while i > 0:
            total += self.tree[i]
            i -= i & -i
        return total
        

class SegmentTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (4 * n)

    def update(self, i, val):
        self.updateTree(0, self.n - 1, i, val, 0)

    def updateTree(self, qlo, qhi, qIndex, val, tIndex):
        if qlo == qhi:
            self.tree[tIndex] = val
            return
        
        mid = qlo + (qhi - qlo) // 2
        if qIndex <= mid:
            self.updateTree(qlo, mid, qIndex, val, 2*tIndex+1)
        else:
            self.updateTree(mid+1, qhi, qIndex, val, 2*tIndex+2)
        self.tree[tIndex] = self.tree[2*tIndex+1] + self.tree[2*tIndex+2]

    def query(self, start, end):
        if start > end:
            return 0
        return self.queryTree(start, end, 0, self.n-1, 0)
    
    def queryTree(self, start, end, qlo, qhi, tIndex):
        if qlo == start and qhi == end:
            return self.tree[tIndex]
        
        mid = qlo + (qhi - qlo) // 2
        if end <= mid:
            return self.queryTree(start, end, qlo, mid, 2*tIndex+1)
        elif start > mid:
            return self.queryTree(start, end, mid+1, qhi, 2*tIndex+2)
        else:
            return self.queryTree(start, mid, qlo, mid, 2*tIndex+1) \
            + self.queryTree(mid+1, end, mid+1, qhi, 2*tIndex+2)

class Solution:
    # def countOfPeaks(self, nums: List[int], queries: List[List[int]]) -> List[int]:
    #     n = len(nums)
    #     tree = SegmentTree(n)
    #     for i in range(1, n - 1):
    #         # peak found
    #         if self.isPeak(i, nums):
    #             tree.update(i, 1)

    #     res = []
    #     for query in queries:
    #         # get count of peak elements in subarray
    #         if query[0] == 1:
    #             # ignore first and last element in subarray
    #             res.append(tree.query(query[1]+1, query[2]-1))
    #         else:
    #             # update peaks
    #             i, val = query[1], query[2]
    #             prev = [self.isPeak(i-1, nums), self.isPeak(i, nums), self.isPeak(i+1, nums)]
    #             nums[i] = val
    #             # see if prev, curr, next index needs updating
    #             for p, before in enumerate(prev):
    #                 if before != self.isPeak(i+p-1, nums):
    #                     tree.update(i+p-1, 0 if before else 1)

    #     return res

    def countOfPeaks(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        n = len(nums)
        tree = BIT(n)
        for i in range(1, n - 1):
            # peak found
            if self.isPeak(i, nums):
                tree.update(i, 1)

        res = []
        for query in queries:
            # get count of peak elements in subarray
            if query[0] == 1:
                # ignore first and last element in subarray
                res.append(tree.sumRange(query[1]+1, query[2]-1))
            else:
                # update peaks
                i, val = query[1], query[2]
                prev = [self.isPeak(i-1, nums), self.isPeak(i, nums), self.isPeak(i+1, nums)]
                nums[i] = val
                # see if prev, curr, next index needs updating
                for p, before in enumerate(prev):
                    if before != self.isPeak(i+p-1, nums):
                        tree.update(i+p-1, -1 if before else 1)

        return res

    def isPeak(self, i, nums):
        n = len(nums)
        if i > 0 and i < n - 1 and nums[i-1] < nums[i] and nums[i] > nums[i+1]:
            return True
        return False