# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        res = []
        if k == 0:
            res.append(target.val)
        else:
            self.dfs(root, target, k, -1, -1, res)
        return res

    # depthFromTarget is depth of nodes from target as we traverse DOWN
    # from target, distFromTarget is distance from nodes that aren't
    # descendants of target
    def dfs(self, node, target, k, depthFromTarget, distFromTarget, res):
        if not node:
            return -1

        if depthFromTarget == k or distFromTarget == k:
            res.append(node.val)
            # Since this has distance k, we know its neighbors will not
            return -1

        # We have already found target, and we're traversing downwards
        # from it
        if node == target or depthFromTarget > 0:
            if node == target:
                depthFromTarget = 0
            left = self.dfs(node.left, target, k, depthFromTarget + 1, distFromTarget, res)
            right = self.dfs(node.right, target, k, depthFromTarget + 1, distFromTarget, res)
        else:
            # If we've found target, distanceFromTarget > 0
            if distFromTarget > 0:
                distFromTarget += 1
            left = self.dfs(node.left, target, k, -1, distFromTarget, res)
            right = self.dfs(node.right, target, k, -1, distFromTarget, res)

            # K distance from target, don't need to go further
            if left == (k - 1) or right == (k - 1):
                res.append(node.val)
                return -1

            # Found target in left subtree
            if left >= 0:
                self.dfs(node.right, target, k, -1, left + 2, res)
                return left + 1
            
            if right >= 0:
                self.dfs(node.left, target, k, -1, right + 2, res)
                return right + 1

        # By nature, we traverse down then work out way up again,
        # so when we find target, its descendants can be calculated by
        # continuing down, but we have to work our ways back up from target
        # for the other nodes. To signal to a node that target was found
        # in either its left or right subtree, we must return distFromTarget,
        # starting from when we find node
        if node == target:
            return 0

        return -1