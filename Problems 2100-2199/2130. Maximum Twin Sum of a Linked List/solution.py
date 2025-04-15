# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def pairSum(self, head: Optional[ListNode]) -> int:
        # nodeVals = []
        # curr = head
        # while curr:
        #     nodeVals.append(curr.val)
        #     curr = curr.next
        
        # l, r = 0, len(nodeVals) - 1
        # maxTwinSum = 0
        # while l < r:
        #     twinSum = nodeVals[l] + nodeVals[r]
        #     if twinSum > maxTwinSum:
        #         maxTwinSum = twinSum
        #     l += 1
        #     r -= 1

        # return maxTwinSum

        slow, fast = head, head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        slow = self.reverseList(slow)
        maxTwinSum = 0
        while slow:
            twinSum = head.val + slow.val
            if twinSum > maxTwinSum:
                maxTwinSum = twinSum
            head = head.next
            slow = slow.next
        
        return maxTwinSum

    def reverseList(self, head):
        prev, curr = None, head
        while curr:
            nextNode = curr.next
            curr.next = prev
            prev = curr
            curr = nextNode
        return prev