import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLen[i] stores the minimum length of a sub-array with sum = target ending at or before index i
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int sum = 0;
        int left = 0;
        int ans = Integer.MAX_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window from the left if the sum exceeds target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // If we found a sub-array with sum == target
            if (sum == target) {
                int currLen = right - left + 1;
                
                // If there is a valid non-overlapping sub-array before `left`
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[left - 1]);
                }
                
                // Update the minimum length ending at or before `right`
                minSoFar = Math.min(minSoFar, currLen);
            }
            
            minLen[right] = minSoFar;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}