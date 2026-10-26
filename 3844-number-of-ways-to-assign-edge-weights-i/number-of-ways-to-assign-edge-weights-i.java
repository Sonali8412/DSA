import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
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
        
        // Find the maximum depth from root node 1 using BFS
        int maxDepth = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(new int[]{1, 0}); // {node, depth}
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int d = curr[1];
            
            maxDepth = Math.max(maxDepth, d);
            
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[]{v, d + 1});
                }
            }
        }
        
        // The number of ways to choose an odd count of 1s among maxDepth edges is 2^(maxDepth - 1) % (10^9 + 7)
        long mod = 1_000_000_007;
        long ans = 1;
        for (int i = 0; i < maxDepth - 1; i++) {
            ans = (ans * 2) % mod;
        }
        
        return (int) ans;
    }
}