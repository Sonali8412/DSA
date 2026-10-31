import java.util.*;

class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j] stores paths reaching (i, j)
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // 1. Try moving Right from (i, j)
                int rCur = i, cCur = j + 1;
                int dir = 0; // 0 means moving right
                boolean valid = true;

                while (rCur < m && cCur < n && grid[rCur][cCur] == 1) {
                    if (dir == 0) {
                        // hit while moving right -> turn down
                        rCur++;
                        dir = 1; // 1 means moving down
                    } else {
                        // hit while moving down -> turn right
                        cCur++;
                        dir = 0;
                    }
                }

                if (rCur < m && cCur < n) {
                    dp[rCur][cCur] = (dp[rCur][cCur] + dp[i][j]) % MOD;
                }

                // 2. Try moving Down from (i, j)
                rCur = i + 1;
                cCur = j;
                dir = 1; // 1 means moving down
                
                while (rCur < m && cCur < n && grid[rCur][cCur] == 1) {
                    if (dir == 0) {
                        rCur++;
                        dir = 1;
                    } else {
                        cCur++;
                        dir = 0;
                    }
                }

                if (rCur < m && cCur < n) {
                    dp[rCur][cCur] = (dp[rCur][cCur] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}