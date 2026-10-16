class Solution {
    static final int MOD = 1000000007;
    long[] sumTree;
    long[] sqTree;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumTree = new long[4 * n];
        sqTree = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100005];
        java.util.Arrays.fill(last, -1);

        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int l = last[x] + 1;
            int r = j;

            // Update range [l, r] in the segment tree by adding 1 to all counts
            if (l <= r) {
                update(1, 0, n - 1, l, r, 1);
            }

            // The sum of squares for all subarrays ending at index j is stored at the root (or query [0, j])
            long currentSumSquares = querySq(1, 0, n - 1, 0, j);
            totalSum = (totalSum + currentSumSquares) % MOD;

            last[x] = j;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        int len = r - l + 1;
        // sqTree' = sqTree + 2 * val * sumTree + val^2 * len
        long currentSum = sumTree[node];
        sqTree[node] = (sqTree[node] + 2 * val * currentSum + val * val * len) % MOD;
        sumTree[node] = (sumTree[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) update(2 * node, l, mid, ql, qr, val);
        if (qr > mid) update(2 * node + 1, mid + 1, r, ql, qr, val);
        
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        sqTree[node] = (sqTree[2 * node] + sqTree[2 * node + 1]) % MOD;
    }

    private long querySq(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return sqTree[node];
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        long res = 0;
        if (ql <= mid) res = (res + querySq(2 * node, l, mid, ql, qr)) % MOD;
        if (qr > mid) res = (res + querySq(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        return res;
    }
}