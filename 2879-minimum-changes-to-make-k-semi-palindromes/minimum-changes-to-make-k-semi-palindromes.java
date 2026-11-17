class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        // cost[i][j] stores the minimum changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(s, i, j);
            }
        }
        
        // dp[i][p] = min changes to partition prefix of length i into p semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        int INF = 1_000_000;
        for (int i = 0; i <= n; i++) {
            for (int p = 0; p <= k; p++) {
                dp[i][p] = INF;
            }
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome must have length at least 2
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[j][p - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getMinChanges(String s, int start, int end) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;
        
        // Try all valid divisors d (1 <= d < len)
        for (int d = 1; d * 2 <= len; d++) {
            if (len % d != 0) continue;
            
            int currentChanges = 0;
            // Check each of the d interleaved groups
            for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - (d - 1 - offset);
                
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        currentChanges++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, currentChanges);
        }
        
        return minChanges;
    }
}