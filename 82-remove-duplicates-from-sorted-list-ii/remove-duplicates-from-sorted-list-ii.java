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
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node handles cases where the head itself has duplicates
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            // Check if the current node is the start of duplicates
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all nodes with the same value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // Bypass the entire duplicate sequence
                prev.next = curr.next;
            } else {
                // No duplicates found; move prev pointer forward
                prev = prev.next;
            }
            // Move to the next node
            curr = curr.next;
        }

        return dummy.next;
    }
}