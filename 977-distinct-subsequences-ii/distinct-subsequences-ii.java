class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        
        // dp[i] stores the number of distinct subsequences of s[0...i-1]
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: empty subsequence
        
        // Tracks the last seen index for each lowercase English letter (a-z)
        int[] last = new int[26];
        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }
        
        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';
            
            // Double the previous count of distinct subsequences
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            // If character has appeared before, subtract duplicates
            if (last[c] != -1) {
                dp[i] = (dp[i] - dp[last[c] - 1] + MOD) % MOD;
            }
            
            // Update the last seen position of character c
            last[c] = i;
        }
        
        // Subtract 1 to exclude the empty subsequence (since problem asks for non-empty)
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}