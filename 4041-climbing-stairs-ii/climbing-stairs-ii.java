class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;
            for (int i = Math.max(0, j - 3); i < j; i++) {
                int cost = (int) (dp[i] + costs[j - 1] + (long) (j - i) * (j - i));
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            dp[j] = minCost;
        }

        return dp[n];
    }
}