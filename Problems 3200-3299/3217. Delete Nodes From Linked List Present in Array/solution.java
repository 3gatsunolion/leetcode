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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int max = -1;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        boolean[] exclude = new boolean[max + 1];
        for (int num : nums) {
            exclude[num] = true;
        }
        // Set<Integer> numSet = Arrays.stream(nums)
        //                             .boxed()
        //                             .collect(Collectors.toSet());
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            // if (numSet.contains(curr.val)) {
            if (curr.val <= max && exclude[curr.val] == true) {
                prev.next = curr.next;
                curr = curr.next;
                continue;
            }
            curr = curr.next;
            prev = prev.next;
        }
        return dummy.next;
    }
}