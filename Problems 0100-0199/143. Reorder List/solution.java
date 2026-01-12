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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow.next is the head of half way point
        ListNode head2 = reverse(slow.next);
        slow.next = null;
        ListNode head1 = head;
        // interweave (head2 always has <= nodes than head1)
        while (head2 != null) {
            ListNode tmp1 = head1.next;
            head1.next = head2;
            ListNode tmp2 = head2.next;
            head2.next = tmp1;
            head1 = tmp1;
            head2 = tmp2;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        return prev;
    }
}