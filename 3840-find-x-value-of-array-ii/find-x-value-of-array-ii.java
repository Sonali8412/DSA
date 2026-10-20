
import java.util.Arrays;

class Solution {
    private int k;
    private int[] prod;
    private int[][] cnt;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;

        prod = new int[4 * n];
        cnt = new int[4 * n][k];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists for subsequent queries
            update(1, 0, n - 1, idx, val);

            // Count prefix products in nums[start ... n-1]
            int[] result = query(1, 0, n - 1, start, n - 1);
            ans[i] = result[x];
        }

        return ans;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int rem = nums[l] % k;
            prod[node] = rem;
            cnt[node][rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node, node * 2, node * 2 + 1);
    }

    private void merge(int node, int left, int right) {
        prod[node] = (int) ((long) prod[left] * prod[right] % k);

        Arrays.fill(cnt[node], 0);

        // Prefixes entirely inside the left segment
        for (int r = 0; r < k; r++) {
            cnt[node][r] += cnt[left][r];
        }

        // Prefixes extending through the left segment
        // and then into the right segment
        for (int r = 0; r < k; r++) {
            int newRem = (int) ((long) prod[left] * r % k);
            cnt[node][newRem] += cnt[right][r];
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % k;

            prod[node] = rem;
            Arrays.fill(cnt[node], 0);
            cnt[node][rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, val);
        }

        merge(node, node * 2, node * 2 + 1);
    }

    private int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return cnt[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        int[] leftResult = query(node * 2, l, mid, ql, qr);
        int[] rightResult = query(node * 2 + 1, mid + 1, r, ql, qr);

        // Merge the two query results without modifying the tree
        int leftProduct = getProduct(1, 0, n - 1, ql, mid);
        int[] result = new int[k];

        for (int rem = 0; rem < k; rem++) {
            result[rem] += leftResult[rem];
            int newRem = (int) ((long) leftProduct * rem % k);
            result[newRem] += rightResult[rem];
        }

        return result;
    }

    private int getProduct(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return prod[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return getProduct(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return getProduct(node * 2 + 1, mid + 1, r, ql, qr);
        }

        int leftProduct = getProduct(node * 2, l, mid, ql, qr);
        int rightProduct = getProduct(node * 2 + 1, mid + 1, r, ql, qr);

        return (int) ((long) leftProduct * rightProduct % k);
    }
}
