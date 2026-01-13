class SegmentTree:
    def __init__(self, nums):
        n = len(nums)
        self.n = n
        self.tree = [0] * (4 * n)

        self.build(0, nums, 0, len(nums) - 1)

    def build(self, node, nums, start, end):
        if start == end:
            self.tree[node] = nums[start]
            return
        mid = (start + end) // 2
        self.build(2 * node + 1, nums, start, mid)
        self.build(2 * node + 2, nums, mid + 1, end)
        self.tree[node] = max(self.tree[2 * node + 1], self.tree[2 * node + 2])
    
    def update(self, i, val):
        def updateTree(node, start, end):
            if start == end:
                self.tree[node] = val
            else:
                mid = (start + end) // 2
                if start <= i <= mid:
                    updateTree(2 * node + 1, start, mid)
                else:
                    updateTree(2 * node + 2, mid + 1, end)
                self.tree[node] = max(self.tree[2 * node + 1], self.tree[2 * node + 2])
        updateTree(0, 0, self.n - 1)
    
    def findFirst(self, val):

        def find(node, start, end):
            # No need to go further down tree,
            # since maxVal will only decrease not increase
            if val > self.tree[node]:
                return -1
            
            if start == end:
                # self.tree[node] = 0
                return start
            
            mid = (start + end) // 2
            res = find(2 * node + 1, start, mid)
            if res == -1:
                res = find(2 * node + 2, mid + 1, end)
            self.tree[node] = max(self.tree[2 * node + 1], self.tree[2 * node + 2])
            return res
        
        return find(0, 0, self.n - 1)

class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        # Segment Tree
        tree = SegmentTree(baskets)

        unplaced = 0
        for numFruits in fruits:
            basket = tree.findFirst(numFruits)
            if basket == -1:
                unplaced += 1
            else:
                tree.update(basket, 0)
        
        return unplaced
        
        # Sqrt decomposition
        # n = len(baskets)

        # numBuckets = int(ceil(sqrt(n)))

        # buckets = [[] for _ in range(numBuckets)]
        # for i, capacity in enumerate(baskets):
        #     bucketId = i // numBuckets
        #     buckets[bucketId].append((capacity, i))
        
        # for bucket in buckets:
        #     bucket.sort() # Sort by capacity ascending order
        
        # unplaced = 0
        # for numFruits in fruits:
        #     for bucket in buckets:
        #         if not bucket or bucket[-1][0] < numFruits:
        #             continue
        #         chosen = min((i, capacity) for capacity, i in bucket if capacity >= numFruits)
        #         bucket.remove((chosen[1], chosen[0]))
        #         break
        #     else:
        #         unplaced += 1
        
        # return unplaced
        
        # Greedy: TLE
        # unplaced = 0
        # for fruits in fruits:
        #     for i in range(len(baskets)):
        #         if fruits <= baskets[i]:
        #             baskets[i] = 0
        #             break
        #     else:
        #         unplaced += 1
        
        # return unplaced