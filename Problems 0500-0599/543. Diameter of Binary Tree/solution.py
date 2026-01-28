# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        return self.diameter(root)[1]

    def diameter(self, node):
        if not node:
            return [-1, 0]

        onePathL, twoPathL = self.diameter(node.left)
        onePathR, twoPathR  = self.diameter(node.right)

        return [1 + max(onePathL, onePathR), max(2 + onePathL + onePathR, max(twoPathL, twoPathR))]