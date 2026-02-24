# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
    #     def dfs(node, curr_num, nums):
    #         if not node:
    #             return
            
    #         curr_num = (curr_num << 1) | node.val
    #         if not node.left and not node.right:
    #             nums.append(curr_num)
    #             return

    #         dfs(node.left, curr_num, nums)
    #         dfs(node.right, curr_num, nums)
        
    #     nums = []
    #     dfs(root, 0, nums)
    #     return sum(nums)

    # def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
    #     def dfs(node, curr_num):
    #         if not node:
    #             return 0
            
    #         curr_num = (curr_num << 1) | node.val
    #         if not node.left and not node.right:
    #             return curr_num

    #         return dfs(node.left, curr_num) + dfs(node.right, curr_num)
        
    #     return dfs(root, 0)

    # def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
    #     if not root:
    #         return 0

    #     q = deque([[root, root.val]])
    #     res = 0
    #     while q:
    #         node, num = q.popleft()
            
    #         if not node.left and not node.right:
    #             res += num

    #         if node.left:
    #             q.append([node.left, 2 * num + node.left.val])

    #         if node.right:
    #             q.append([node.right, 2 * num + node.right.val])
        
    #     return res

    def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        node = root
        curr_num = 0
        res = 0
        while node:
            if node.left:
                prev = node.left
                count = 1
                while prev.right and prev.right != node:
                    prev = prev.right
                    count += 1
                
                if prev.right == node:
                    prev.right = None
                    if not prev.left:
                        res += curr_num
                    curr_num >>= count
                    node = node.right
                else:
                    prev.right = node
                    curr_num = 2 * curr_num + node.val
                    node = node.left
            else:
                curr_num = 2 * curr_num + node.val
                if not node.right:
                    res += curr_num
                node = node.right
        
        return res