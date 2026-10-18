import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Frequency array to count how many times each difference occurs.
        // Maximum possible difference value is 10^5.
        int maxDiff = 0;
        int[] diffCounts = new int[100001];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCounts[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Greedily reduce the largest differences
        for (int d = maxDiff; d > 0 && totalOps > 0; d--) {
            if (diffCounts[d] == 0) continue;
            
            // Number of elements we can reduce from difference d to d - 1
            long opsToReduce = Math.min(totalOps, (long) diffCounts[d]);
            
            diffCounts[d] -= opsToReduce;
            diffCounts[d - 1] += opsToReduce;
            totalOps -= opsToReduce;
        }
        
        // Calculate the minimum sum of squared differences
        long minSumSqDiff = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (diffCounts[d] > 0) {
                minSumSqDiff += (long) diffCounts[d] * d * d;
            }
        }
        
        return minSumSqDiff;
    }
}