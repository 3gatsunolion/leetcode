/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            return head.next;
        }

        // slow and fast are n nodes apart, so when fast reaches
        // the end, slow will be the nth node from the end
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return head;

        // if (head == null) {
        //     return null;
        // }

        // int count = 0;
        // ListNode curr = head;
        // while (curr != null) {
        //     curr = curr.next;
        //     count++;
        // }
        
        // ListNode dummy = new ListNode(0, head);
        // curr = dummy;
        // for (int i = 0; i < count - n; i++) {
        //     curr = curr.next;
        // }
        // curr.next = curr.next.next;
        // return dummy.next;
    }
}