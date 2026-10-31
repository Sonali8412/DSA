import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Build adjacency list: u -> list of [v, cost]
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int maxEdgeCost = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            graph[u].add(new int[]{v, cost});
            maxEdgeCost = Math.max(maxEdgeCost, cost);
        }
        
        int low = 0, high = maxEdgeCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(n, graph, online, k, mid)) {
                ans = mid;         // Valid path found with min edge cost >= mid
                low = mid + 1;     // Try for a larger minimum edge cost
            } else {
                high = mid - 1;    // Try a smaller minimum edge cost
            }
        }
        
        return ans;
    }
    
    private boolean canReach(int n, List<int[]>[] graph, boolean[] online, long k, int minCost) {
        // If start or end nodes are offline (though constraints say 0 and n-1 are online)
        if (!online[0] || !online[n - 1]) return false;
        
        // minCost[u] stores the minimum total recovery cost to reach node u
        long[] minDist = new long[n];
        Arrays.fill(minDist, Long.MAX_VALUE);
        
        // Priority queue storing {accumulated_cost, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        minDist[0] = 0;
        pq.offer(new long[]{0, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            
            if (u == n - 1) return true; // Reached target within cost limit
            if (d > minDist[u]) continue;
            
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int cost = neighbor[1];
                
                // Skip if edge cost is less than our binary search target
                if (cost < minCost) continue;
                
                // Intermediate nodes must be online
                if (v != n - 1 && !online[v]) continue;
                
                long newDist = d + cost;
                if (newDist <= k && newDist < minDist[v]) {
                    minDist[v] = newDist;
                    pq.offer(new long[]{newDist, v});
                }
            }
        }
        
        return minDist[n - 1] <= k;
    }
}