# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        prev = slow
        slow = slow.next
        prev.next = None
        while slow:
            temp = slow.next
            slow.next = prev
            prev = slow
            slow = temp
        
        one, two = head, prev
        while two:
            if one.val != two.val: return False
            one, two = one.next, two.next
        
        return True