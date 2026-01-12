# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if not root:
            return False
        return self.solve(root, 0, targetSum)

    def solve(self, root, currSum, targetSum):
        if not root:
            return False

        if not root.left and not root.right:
            return currSum + root.val == targetSum

        return self.solve(root.left, currSum + root.val, targetSum) or \
        self.solve(root.right, currSum + root.val, targetSum)