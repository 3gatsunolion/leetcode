# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # def isBalanced(self, root: Optional[TreeNode]) -> bool:
    #     def balanced(node):
    #         if not node:
    #             return 0, True
            
    #         left_height, left_balanced = balanced(node.left)
    #         right_height, right_balanced = balanced(node.right)

    #         if not left_balanced or not right_balanced:
    #             return 0, False

    #         if abs(left_height - right_height) > 1:
    #             return 0, False

    #         return (1 + max(left_height, right_height), True)
        
    #     return balanced(root)[1]

    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        def balanced(node):
            if not node:
                return 0
            
            left = balanced(node.left)
            if left == -1:
                return -1
            
            right = balanced(node.right)
            if right == -1:
                return -1

            if abs(left - right) > 1:
                return -1
            
            return 1 + max(left, right)
        
        return balanced(root) != -1
