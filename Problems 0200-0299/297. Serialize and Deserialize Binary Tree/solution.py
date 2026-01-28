# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# DFS
# class Codec:

#     def serialize(self, root):
#         """Encodes a tree to a single string.
        
#         :type root: TreeNode
#         :rtype: str
#         """
        
#         def dfs(node, encode):
#             if not node:
#                 encode.append('#')
#                 return
            
#             encode.append(str(node.val))
#             dfs(node.left, encode)
#             dfs(node.right, encode)
        
#         encode = []
#         dfs(root, encode)
#         return ",".join(encode)
        

#     def deserialize(self, data):
#         """Decodes your encoded data to tree.
        
#         :type data: str
#         :rtype: TreeNode
#         """
#         # if data == "#":
#         #     return None
        
#         # nodes = data.split(',')
#         # root = TreeNode(int(nodes[0]))

#         # stack = [root]

#         # i = 1

#         # while stack:
#         #     # If we're processing a node here, that means not
#         #     # all of its children has been processed yet
#         #     curr = stack[-1]

#         #     if curr.left is None:
#         #         val = nodes[i]
#         #         i += 1
#         #         # Add left child and continue (since we're processing
#         #         # all left childs first)
#         #         if val != '#':
#         #             curr.left = TreeNode(int(val))
#         #             stack.append(curr.left)
#         #             continue

#         #     # If code reaches here that means curr.left is filled,
#         #     # so we need to fill the right child now
#         #     # And since we're filling the right child, we've finished
#         #     # processing this node, so we can pop it off the stack
#         #     stack.pop()
            
#         #     # if curr.right is None:
#         #     val = nodes[i]
#         #     i += 1
#         #     if val != '#':
#         #         curr.right = TreeNode(int(val))
#         #         stack.append(curr.right)
        
#         # return root

#         if data == "#":
#             return None
        
#         nodes = data.split(',')
#         nodes = iter(nodes)

#         def dfs(nodes):
#             val = next(nodes)

#             if val == '#':
#                 return None
            
#             node = TreeNode(int(val))
#             node.left = dfs(nodes)
#             node.right = dfs(nodes)
#             return node

#         return dfs(nodes)

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return "#"
        
        q = deque([root])
        res = []
        while q:
            node = q.popleft()

            if node:
                res.append(str(node.val))
                q.append(node.left)
                q.append(node.right)
            else:
                res.append('#')

        return ",".join(res)

        

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if data == "#":
            return None
        
        nodes = data.split(',')
        root = TreeNode(int(nodes[0]))
        q = deque([root])
        i = 1
        
        while i < len(nodes):
            node = q.popleft()
            if nodes[i] != '#':
                node.left = TreeNode(int(nodes[i]))
                q.append(node.left)
            
            i += 1
            if nodes[i] != '#':
                node.right = TreeNode(int(nodes[i]))
                q.append(node.right)
            
            i += 1
        
        return root

            
        

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))