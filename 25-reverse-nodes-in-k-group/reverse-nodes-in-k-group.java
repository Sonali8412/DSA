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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Check if there are at least k nodes left to reverse
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If less than k nodes remain, return head as is
        if (count < k) {
            return head;
        }
        
        // Reverse the k nodes
        ListNode prev = null;
        curr = head;
        ListNode nextNode = null;
        
        for (int i = 0; i < k; i++) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        
        // Recursively process the rest of the list and connect it
        head.next = reverseKGroup(curr, k);
        
        // 'prev' is the new head of the reversed k-group
        return prev;
    }
}