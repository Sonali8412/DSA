import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int K = 18; // sufficient for up to 5 * 10^4 elements
        int[][] maxSt = new int[K][n];
        int[][] minSt = new int[K][n];
        int[] logTable = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        for (int i = 0; i < n; i++) {
            maxSt[0][i] = nums[i];
            minSt[0][i] = nums[i];
        }

        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxSt[j][i] = Math.max(maxSt[j - 1][i], maxSt[j - 1][i + (1 << (j - 1))]);
                minSt[j][i] = Math.min(minSt[j - 1][i], minSt[j - 1][i + (1 << (j - 1))]);
            }
        }

        // Helper function to query range max in O(1)
        // (Defined via lambda or inline logic)
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> -a[0]));

        for (int l = 0; l < n; l++) {
            int r = n - 1;
            long val = queryMax(l, r, maxSt, minSt, logTable) - queryMin(l, r, maxSt, minSt, logTable);
            pq.offer(new long[]{val, l, r});
        }

        long totalValue = 0;
        for (int i = 0; i < k; i++) {
            long[] curr = pq.poll();
            long val = curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];

            totalValue += val;

            if (r > l) {
                long nextVal = queryMax(l, r - 1, maxSt, minSt, logTable) - queryMin(l, r - 1, maxSt, minSt, logTable);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }

        return totalValue;
    }

    private int queryMax(int L, int R, int[][] maxSt, int[][] minSt, int[] logTable) {
        int j = logTable[R - L + 1];
        return Math.max(maxSt[j][L], maxSt[j][R - (1 << j) + 1]);
    }

    private int queryMin(int L, int R, int[][] maxSt, int[][] minSt, int[] logTable) {
        int j = logTable[R - L + 1];
        return Math.min(minSt[j][L], minSt[j][R - (1 << j) + 1]);
    }
}