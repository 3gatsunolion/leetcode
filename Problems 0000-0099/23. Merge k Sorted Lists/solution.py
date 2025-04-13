# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

import heapq
class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        # return self.mergeKListsRange(lists, 0, len(lists) - 1)

        k = len(lists)
        h = []
        for i in range(k):
            if not lists[i]:
                continue
            heapq.heappush(h, (lists[i].val, i, lists[i]))
        
        dummy = curr = ListNode()
        while h:
            _, i, node = heapq.heappop(h)
            curr.next = node
            curr = curr.next
            if node.next:
                heapq.heappush(h, (node.next.val, i, node.next))
        
        return dummy.next

    # def mergeKListsRange(self, lists, start, end):
    #     if start > end:
    #         return
    #     if start == end:
    #         return lists[start]
        
    #     mid = start + (end - start) // 2
    #     left = self.mergeKListsRange(lists, start, mid)
    #     right = self.mergeKListsRange(lists, mid + 1, end)
    #     return self.mergeLists(left, right)

    # def mergeLists(self, l1, l2):
    #     dummy = ListNode()
    #     curr = dummy
    #     while l1 or l2:
    #         if l1 and l2:
    #             if l1.val <= l2.val:
    #                 curr.next = l1
    #                 l1 = l1.next
    #             else:
    #                 curr.next = l2
    #                 l2 = l2.next
    #         elif l1:
    #             curr.next = l1
    #             l1 = l1.next
    #         else:
    #             curr.next = l2
    #             l2 = l2.next
    #         curr = curr.next
        
    #     return dummy.next
        