
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX = 1024;

        boolean[][] dp = new boolean[n][MAX];
        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            boolean[][] nextDp = new boolean[n][MAX];

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    nextDp[j][grid[i][j]] = true;
                    continue;
                }

                for (int x = 0; x < MAX; x++) {
                    // Come from top
                    if (i > 0 && dp[j][x]) {
                        nextDp[j][x ^ grid[i][j]] = true;
                    }

                    // Come from left in the current row
                    if (j > 0 && nextDp[j - 1][x]) {
                        nextDp[j][x ^ grid[i][j]] = true;
                    }
                }
            }

            dp = nextDp;
        }

        for (int x = 0; x < MAX; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }

        return 0;
    }
}
