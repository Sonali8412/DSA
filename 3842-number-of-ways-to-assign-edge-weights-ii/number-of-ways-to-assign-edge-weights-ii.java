import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] depth;
    private int[][] up;
    private int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        LOG = 32 - Integer.numberOfLeadingZeros(n);
        depth = new int[n + 1];
        up = new int[n + 1][LOG];
        
        // Precompute binary lifting and depths using BFS/DFS
        dfs(1, 0, 0, adj);
        
        int qLen = queries.length;
        int[] ans = new int[qLen];
        
        for (int i = 0; i < qLen; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = getLCA(u, v);
            
            // Distance between u and v in terms of edge count
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            
            if (dist == 0) {
                ans[i] = 0;
            } else {
                // Number of ways to choose an odd count of 1s among 'dist' edges is 2^(dist - 1)
                ans[i] = (int) power(2, dist - 1);
            }
        }
        
        return ans;
    }
    
    private void dfs(int u, int p, int d, List<List<Integer>> adj) {
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, d + 1, adj);
            }
        }
    }
    
    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        
        if (u == v) return u;
        
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        
        return up[u][0];
    }
    
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}