from collections import deque
import heapq
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        if not root:
            return -1
        # bfs
        q = deque([root])
        minHeap = []
        while q:
            n = len(q)
            levelSum = 0
            for _ in range(n):
                node = q.popleft()
                levelSum += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            heapq.heappush(minHeap, levelSum)
            if len(minHeap) > k:
                heapq.heappop(minHeap)

        if len(minHeap) < k:
            return -1
        
        return minHeap[0]