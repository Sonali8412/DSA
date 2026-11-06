import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
        
        // Step 1: Find all suspicious methods starting from k using DFS
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);
        
        // Step 2: Check if any non-suspicious method invokes a suspicious method
        boolean invokedFromOutside = false;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                invokedFromOutside = true;
                break;
            }
        }
        
        // Step 3: If invoked from outside, no methods can be removed. Otherwise, remove all suspicious ones.
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (invokedFromOutside || !isSuspicious[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private void dfs(int u, List<List<Integer>> graph, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        for (int v : graph.get(u)) {
            if (!isSuspicious[v]) {
                dfs(v, graph, isSuspicious);
            }
        }
    }
}