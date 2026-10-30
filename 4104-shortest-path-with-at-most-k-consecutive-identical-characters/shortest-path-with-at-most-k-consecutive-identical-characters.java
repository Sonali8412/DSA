import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }

        // Priority queue stores: [current_weight, node, count]
        // Ordered by current_weight ascending
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Distances table: dist[node][count] stores the min weight to reach this state
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Initial state at node 0
        int startNode = 0;
        int startCount = 1;
        pq.offer(new long[]{0, startNode, startCount});
        dist[startNode][startCount] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            // If we reached the target node n - 1, return the total weight
            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Check constraint
                if (nextCount <= k) {
                    if (d + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = d + weight;
                        pq.offer(new long[]{d + weight, v, nextCount});
                    }
                }
            }
        }

        // If node n - 1 cannot be reached within the constraints
        return -1;
    }
}