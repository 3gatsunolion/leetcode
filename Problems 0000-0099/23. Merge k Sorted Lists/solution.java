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
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode l1 = mergeKLists(lists, start, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, end);

        return mergeLists(l1, l2);
    }

    public ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (l1 != null || l2 != null) {
            if (l2 == null || (l1 != null && l1.val < l2.val)) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    // public ListNode mergeKLists(ListNode[] lists) {
    //     PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

    //     for (ListNode node : lists) {
    //         if (node == null) {
    //             continue;
    //         }
    //         minHeap.add(node);
    //     }
        
    //     ListNode dummy = new ListNode();
    //     ListNode curr = dummy;
    //     while (!minHeap.isEmpty()) {
    //         ListNode node = minHeap.poll();
    //         curr.next = node;
    //         curr = curr.next;

    //         if (node.next != null) {
    //             minHeap.add(node.next);
    //         }
    //     }
    //     return dummy.next;
    // }
}