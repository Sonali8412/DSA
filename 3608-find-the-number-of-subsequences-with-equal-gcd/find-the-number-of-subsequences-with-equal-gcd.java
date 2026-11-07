import java.util.Arrays;

class Solution {
    public int subsequencePairCount(int[] nums) {
        int MAX = 200;
        int MOD = 1_000_000_007;
        
        // dp[g1][g2] stores the number of disjoint subsequence pairs 
        // with the GCD of the first subsequence being g1 and the second being g2.
        // g1 = 0 or g2 = 0 represents an empty subsequence.
        long[][] dp = new long[MAX + 1][MAX + 1];
        
        // Base case: empty pair (0, 0) has 1 way
        dp[0][0] = 1;
        
        for (int x : nums) {
            long[][] nextDp = new long[MAX + 1][MAX + 1];
            for (int i = 0; i <= MAX; i++) {
                for (int j = 0; j <= MAX; j++) {
                    if (dp[i][j] == 0) continue;
                    
                    // Option 1: Do not include x in either subsequence
                    nextDp[i][j] = (nextDp[i][j] + dp[i][j]) % MOD;
                    
                    // Option 2: Include x in the first subsequence
                    int newG1 = (i == 0) ? x : gcd(i, x);
                    nextDp[newG1][j] = (nextDp[newG1][j] + dp[i][j]) % MOD;
                    
                    // Option 3: Include x in the second subsequence
                    int newG2 = (j == 0) ? x : gcd(j, x);
                    nextDp[i][newG2] = (nextDp[i][newG2] + dp[i][j]) % MOD;
                }
            }
            dp = nextDp;
        }
        
        long totalPairs = 0;
        // Sum all valid non-empty subsequence pairs where g1 == g2
        for (int g = 1; g <= MAX; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }
        
        return (int) totalPairs;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}