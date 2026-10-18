class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] will store the number of distinct subsequences of prefix of s 
        // that match prefix of t of length j.
        // We use a 1D array optimized from 2D DP.
        int[] dp = new int[n + 1];
        
        // Base case: empty string t can be formed by any prefix of s in 1 way
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to use values from the previous row correctly
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}