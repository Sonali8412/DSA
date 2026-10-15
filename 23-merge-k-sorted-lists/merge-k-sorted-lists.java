import java.util.PriorityQueue;

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
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Min-heap ordered by the node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add the head of each non-empty list into the priority queue
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        
        while (!minHeap.isEmpty()) {
            // Extract the smallest node
            ListNode smallestNode = minHeap.poll();
            current.next = smallestNode;
            current = current.next;
            
            // If there is a next node in the same list, add it to the heap
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }
        
        return dummyHead.next;
    }
}