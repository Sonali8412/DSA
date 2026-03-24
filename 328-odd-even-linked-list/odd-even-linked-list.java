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
    public ListNode oddEvenList(ListNode head) {

        if (head == null) return null;

        // dummy nodes
        ListNode even = new ListNode(-1);
        ListNode et = even;

        ListNode odd = new ListNode(-1);
        ListNode ot = odd;

        ListNode curr = head;
        int count = 1;

        while (curr != null) {

            if (count % 2 == 0) {   // even position
                et.next = curr;
                et = et.next;
            } else {                // odd position
                ot.next = curr;
                ot = ot.next;
            }

            curr = curr.next;   // move forward
            count++;
        }

        // terminate even list
        et.next = null;

        // attach even list after odd list
        ot.next = even.next;

        return odd.next;
    }
}