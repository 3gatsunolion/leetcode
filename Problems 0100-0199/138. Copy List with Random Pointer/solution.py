"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':

        # 1. Interleave list with copy
        # h1 - h1' - h2 - h2' - h3 - h3'
        # 2. Go back at each original node, go to random node, whose copy
        # will be next to assign to copy nodes
        # 3. Restore

        curr = head

        while curr:
            copy = Node(curr.val)
            copy.next = curr.next
            curr.next = copy
            curr = copy.next
        
        curr = head
        while curr:
            copy = curr.next
            if curr.random:
                copy.random = curr.random.next
            curr = copy.next
        
        curr = head
        dummy = prevCopy = Node(-1)
        while curr:
            # Restore
            copy = curr.next
            curr.next = copy.next

            # Connect copy
            prevCopy.next = copy
            prevCopy = prevCopy.next

            curr = copy.next
        
        return dummy.next
        
        # copy = {}
        # dummy = Node(0)
        # prevCopy = dummy
        # curr = head
        # while curr:
        #     if curr not in copy:
        #         copy[curr] = Node(curr.val)
        #     copyNode = copy[curr]
        #     prevCopy.next = copyNode
        #     prevCopy = prevCopy.next
        #     if curr.random:
        #         if curr.random not in copy:
        #             copy[curr.random] = Node(curr.random.val)
        #         randomCopy = copy[curr.random]
        #         copyNode.random = randomCopy
        #     curr = curr.next
        
        # return dummy.next