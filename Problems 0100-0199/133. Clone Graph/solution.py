"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional
class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node:
            return node
        
        mp = {}

        def clone(node):
            if node in mp:
                return mp[node]

            cloneNode = Node(val=node.val)
            mp[node] = cloneNode

            for adj in node.neighbors:
                if adj not in mp:
                    clone(adj)
                adjClone = mp[adj]
                cloneNode.neighbors.append(adjClone)
            
            return cloneNode

        return clone(node)