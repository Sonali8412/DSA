import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        int n = nums.length;
        long totalSubarrays = 0;
        long currentCost = 0;
        
        // Store indices of `nums` with `nums[i]` in non-increasing order.
        Deque<Integer> dq = new ArrayDeque<>();
        int j = n - 1;
        
        for (int i = n - 1; i >= 0; --i) {
            final int num = nums[i];
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                // Adjust cost contributions when elements are popped
                currentCost += (long) (r - l) * (num - nums[l]);
            }
            dq.offerLast(i);
            
            // Shrink window from the right if currentCost exceeds k
            while (currentCost > k) {
                currentCost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                --j;
            }
            
            totalSubarrays += (j - i + 1);
        }
        
        return totalSubarrays;
    }
}