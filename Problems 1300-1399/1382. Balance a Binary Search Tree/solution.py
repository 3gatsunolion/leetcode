# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 1. Inorder Traversal
    # Time: O(n)
    # Space: O(n)
    # def balanceBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    #     def inorder(node, nodes):
    #         if not node:
    #             return
    #         inorder(node.left, nodes)
    #         nodes.append(node)
    #         inorder(node.right, nodes)

    #     def balance(nodes, l, r):
    #         if l > r:
    #             return None
    #         mid = (l + r) // 2
    #         node = nodes[mid]
    #         node.left = balance(nodes, l, mid - 1)
    #         node.right = balance(nodes, mid + 1, r)
    #         return node
        
    #     nodes = []
    #     inorder(root, nodes)
    #     return balance(nodes, 0, len(nodes) - 1)

    # 2. Day-Stout-Warren (DSW) Algorithm / In-Place Balancing
    # Time: O(n)
    # Space: O(1)
    def balanceBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def make_vine(dummy):
            if not dummy.right:
                return 0

            tail = dummy
            curr = dummy.right
            count = 0
            while curr:
                if curr.left:
                    # Right rotate
                    new_right = curr
                    curr = curr.left
                    curr.right, new_right.left = new_right, curr.right
                    tail.right = curr
                else:
                    count += 1
                    tail = curr
                    curr = curr.right
            return count

        def compress(dummy, count):
            prev = dummy
            for _ in range(count):
                child = prev.right
                grandchild = child.right

                # Left rotation
                prev.right = grandchild
                child.right = grandchild.left
                grandchild.left = child

                prev = prev.right
            
        # 1. Turn tree into a single sorted linked list by
        # right rotating until the tree is flattened
        dummy = TreeNode(0, right=root)
        total_nodes = make_vine(dummy)
        # vine = dummy.right

        # 2. Balance tree through left rotations
        # Note: A perfect binary tree (fully filled)
        # has 2^(h + 1) - 1 nodes, where h is the height
        # Let n = 2^(h + 1) - 1
        # -> n + 1 = 2^(h + 1)
        # -> log2(n + 1) = h + 1
        # -> h = log2(n + 1) - 1 -> height of closest perfect binary tree
        h = int(math.log2(total_nodes + 1)) - 1
        num_nodes_in_perfect_binary_tree = 2**(h + 1) - 1
        leftover_nodes = total_nodes - num_nodes_in_perfect_binary_tree

        # The leftover nodes will be on the bottom left side
        # of the tree as leafs with the full perfect binary
        # tree above them
        compress(dummy, leftover_nodes)
        
        # Now vine has height num_nodes_in_perfect_binary_tree
        # we'll perform left rotations on every other node
        # to balance the tree, and each time we do this, we 
        # are building out one level of the tree
        # so we want to do this log2(total_nodes) times, as that
        # will give us log2(total_nodes) height, which is what we
        # want for a perfect binary tree
        m = num_nodes_in_perfect_binary_tree
        m //= 2
        while m > 0:
            compress(dummy, m)
            m //= 2
        
        return dummy.right
