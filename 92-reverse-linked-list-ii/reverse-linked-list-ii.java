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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        // Dummy node to handle edge cases where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 1. Move `prev` to the node just before the `left` position
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 2. `curr` points to the first node of the sublist to be reversed
        ListNode curr = prev.next;

        // 3. Reverse the sublist from `left` to `right` in-place
        ListNode nextNode = null;
        ListNode prevSub = null;
        
        for (int i = 0; i <= right - left; i++) {
            nextNode = curr.next;
            curr.next = prevSub;
            prevSub = curr;
            curr = nextNode;
        }

        // 4. Reconnect the reversed sublist with the rest of the linked list
        prev.next.next = curr; // Connect tail of reversed part to the remaining list
        prev.next = prevSub;   // Connect pre-sublist node to the new head of the reversed part

        return dummy.next;
    }
}