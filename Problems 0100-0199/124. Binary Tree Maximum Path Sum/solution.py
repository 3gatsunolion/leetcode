# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        
        def findMaxPathSum(root):
            if not root:
                return [float("-inf"), float("-inf")]
        
            onePathL, maxL = findMaxPathSum(root.left)
            onePathR, maxR = findMaxPathSum(root.right)

            onePath = max(root.val, root.val + max(onePathL, onePathR))

            maxPath = max(max(maxL, maxR), max(onePath,
            root.val + onePathL + onePathR))
            return [onePath, maxPath]

        return findMaxPathSum(root)[1]