# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        subtreeSums = []

        def getTreeSum(node):
            if not node:
                return 0
            
            treeSum = node.val + getTreeSum(node.left) + getTreeSum(node.right)
            subtreeSums.append(treeSum)
            return treeSum
        
        total = getTreeSum(root)
        return max(val * (total - val) for val in subtreeSums) % (10**9 + 7)