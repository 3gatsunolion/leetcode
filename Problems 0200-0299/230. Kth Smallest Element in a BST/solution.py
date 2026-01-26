# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        # Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
        # Store number of nodes in each subtree -> Insert and delete would update these counts and would remain O(h).
        # Lookup would take O(h) time too instead of O(n)
        def process(root):
            if not root: return 0
            leftCount = process(root.left)
            rightCount = process(root.right)
            total = leftCount + rightCount + 1
            root.val = (root.val, total)
            return total
        
        def findKth(root, k):
            if not root:
                return -1
            leftCount = root.left.val[1] if root.left else 0
            rightCount = root.right.val[1] if root.right else 0
            if k <= leftCount:
                return findKth(root.left, k)
            elif k == leftCount + 1:
                return root.val[0]
            else:
                return findKth(root.right, k - (leftCount + 1))
        
        process(root)
        return findKth(root, k)
        # count = [0]
        # kth = [0]
        
        # def findKth(node):
        #     if not node:
        #         return
            
        #     findKth(node.left)
        #     count[0] += 1

        #     if count[0] == k:
        #         kth[0] = node.val
        #         return

        #     if count[0] < k:
        #         findKth(node.right)
        
        # findKth(root)
        # return kth[0]