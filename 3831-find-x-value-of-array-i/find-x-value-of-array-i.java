class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        
        // dp[r] stores the number of subarrays ending at the current position with product modulo k equal to r
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentMod = (int) (num % k);
            
            // Single-element subarray starting at the current position
            nextDp[currentMod]++;
            
            // Extend all previous subarrays ending at the previous position
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newMod = (int) ((r * (long) num) % k);
                    nextDp[newMod] += dp[r];
                }
            }
            
            dp = nextDp;
            
            // Add counts from the current position's subarrays to the global result
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}