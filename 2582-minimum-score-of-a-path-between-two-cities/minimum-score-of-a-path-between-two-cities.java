import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build adjacency list: node -> list of [neighbor, distance]
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            int u = road[0], v = road[1], distance = road[2];
            graph[u].add(new int[]{v, distance});
            graph[v].add(new int[]{u, distance});
        }
        
        // BFS traversal from city 1
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        
        int minScore = Integer.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] neighbor : graph[curr]) {
                int nextNode = neighbor[0];
                int distance = neighbor[1];
                
                // Update the minimum road distance found in this component
                minScore = Math.min(minScore, distance);
                
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return minScore;
    }
}