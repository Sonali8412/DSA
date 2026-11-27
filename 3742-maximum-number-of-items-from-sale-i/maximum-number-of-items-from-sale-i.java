class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeFromFirstBuy = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int pi = items[i][1];
            if (pi < minPrice) {
                minPrice = pi;
            }

            int freeCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    freeCount++;
                }
            }
            freeFromFirstBuy[i] = freeCount;
        }

        // dp[b] = max copies from first-time purchases of distinct item types using budget b
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstPurchaseCopies = 1 + freeFromFirstBuy[i];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstPurchaseCopies);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            int extraCopies = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extraCopies);
        }

        return ans;
    }
}