# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
# class Solution:
#     # Let m = number of nodes in root
#     # n = number of nodes in subRoot
#     # Time complexity: O(m*n) -> worst case for each node in root
#     # we compare all n nodes with subRoot
#     # Space complexity: O(height of root)
#     def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
#         def isSameTree(n1, n2):
#             if not n1 or not n2:
#                 return n1 == n2
            
#             return n1.val == n2.val and isSameTree(n1.left, n2.left) and isSameTree(n1.right, n2.right)
        
#         if not subRoot:
#             return True
        
#         if not root:
#             return False
        
#         if isSameTree(root, subRoot):
#             return True
        
#         return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)

# class Solution:
#     # Merkle hashes
#     # Hash each subtree, so when we arrive at a node, we can
#     # immediately compare hashes to see if they are the same tree
#     # Time complexity: O(m + n)
#     # Space: O(height of m)
#     def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
#         from hashlib import sha256
#         def hash_(x):
#             s = sha256(x.encode('utf-8'))
#             return s.hexdigest()
        
#         def merkle(node):
#             if not node:
#                 return '#'
            
#             node.merkle = hash_(merkle(node.left) + str(node.val) + merkle(node.right))
#             return node.merkle
        
#         merkle(root)
#         merkle(subRoot)

#         def dfs(node):
#             if not node:
#                 return False
#             return node.merkle == subRoot.merkle or \
#                     dfs(node.left) or dfs(node.right)
        
#         return dfs(root)

class Solution:
    # Serialize trees, then find subRoot string in root string
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        def serialize(node):
            def inOrder(node):
                if not node:
                    res.append('#')
                    return
                
                res.append(str(node.val))
                inOrder(node.left)
                inOrder(node.right)
            
            res = []
            inOrder(node)
            # IMPORTANT: "," at the start because 12,#,# will return
            # true if we search 2,#,# -> but if we have
            # ,12,#,# -> we get false for ,2,#,#
            return "," + ",".join(res)

        def getLPSTable(s):
            n = len(s)
            # table[i] -> if there's mismatch when matching at
            # s[i + 1], table[i] tells us where to start matching
            # again at s
            table = [0] * n

            currLPS = 0
            for i in range(1, n):
                if currLPS > 0 and s[currLPS] != s[i]:
                    currLPS = table[currLPS - 1]
                
                if s[i] == s[currLPS]:
                    currLPS += 1
                
                table[i] = currLPS
            
            return table
        
        rootSer = serialize(root)
        subRootSer = serialize(subRoot)

        lookup = getLPSTable(subRootSer)
        i = 0
        for c in rootSer:
            while i > 0 and c != subRootSer[i]:
                i = lookup[i - 1]
            
            if c == subRootSer[i]:
                i += 1

            if i == len(subRootSer):
                return True
        
        return False

        # return subRootSer in rootSer