import java.util.*;

class Solution {
    // Segment Tree arrays and tree structures
    private int[] head, to, next, edgeHead;
    private int edgeCount = 0;
    private int[] tin, tout, rank, parent, depth, heavy, headNode, pos;
    private int timer = 0;
    private int[] tree;
    private int[] maskArray;
    private char[] charArray;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = edgeHead[u];
        edgeHead[u] = edgeCount++;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        edgeHead = new int[n];
        Arrays.fill(edgeHead, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        edgeCount = 0;

        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
            addEdge(edge[1], edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        headNode = new int[n];
        pos = new int[n];
        charArray = s.toCharArray();

        dfs1(0, -1, 0);
        dfs2(0, 0);

        tree = new int[4 * n];
        maskArray = new int[n];
        for (int i = 0; i < n; i++) {
            maskArray[pos[i]] = 1 << (charArray[i] - 'a');
        }
        build(1, 0, n - 1);

        List<Boolean> result = new ArrayList<>();

        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = 1 << (charArray[u] - 'a');
                int newMask = 1 << (c - 'a');
                charArray[u] = c;
                update(1, 0, n - 1, pos[u], newMask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v, n);
                // A palindrome condition: at most one bit is set in the mask
                boolean isPalindrome = (pathMask & (pathMask - 1)) == 0;
                result.add(isPalindrome);
            }
        }

        return result;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtreeSize = 0;
        for (int e = edgeHead[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                int subSize = dfs1(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubtreeSize) {
                    maxSubtreeSize = subSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        headNode[u] = h;
        pos[u] = timer++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int e = edgeHead[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = maskArray[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int querySegment(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return querySegment(2 * node, start, mid, l, r) ^ querySegment(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPath(int u, int v, int n) {
        int res = 0;
        while (headNode[u] != headNode[v]) {
            if (depth[headNode[u]] > depth[headNode[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= querySegment(1, 0, n - 1, pos[headNode[v]], pos[v]);
            v = parent[headNode[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= querySegment(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}