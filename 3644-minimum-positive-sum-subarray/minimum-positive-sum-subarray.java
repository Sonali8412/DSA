import java.util.List;
import java.util.Collections;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate over all possible starting points of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Iterate over all possible lengths from l to r
            for (int j = i; j < n && (j - i + 1) <= r; j++) {
                currentSum += nums.get(j);
                
                // Check if the current subarray length is at least l and sum is positive
                if ((j - i + 1) >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}