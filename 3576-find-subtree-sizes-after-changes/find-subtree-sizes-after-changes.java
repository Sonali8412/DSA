import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private List<Integer>[] newAdj;
    private int[] lastSeen;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        adj = new ArrayList[n];
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }

        // Build the initial tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // Track the most recent ancestor for each character 'a' - 'z'
        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // First DFS: Determine the new parent for each node simultaneously
        findNewParents(0, -1);

        // Second DFS: Calculate subtree sizes on the updated tree
        ans = new int[n];
        calculateSizes(0);

        return ans;
    }

    private void findNewParents(int u, int p) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (u != 0) {
            // If an ancestor with the same character exists, it becomes the new parent
            int newParent = (prevAncestor != -1) ? prevAncestor : p;
            newAdj[newParent].add(u);
        }

        // Update the most recent node seen for this character
        lastSeen[charIdx] = u;

        for (int v : adj[u]) {
            findNewParents(v, u);
        }

        // Backtrack: restore the previous ancestor for this character
        lastSeen[charIdx] = prevAncestor;
    }

    private int calculateSizes(int u) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += calculateSizes(v);
        }
        ans[u] = size;
        return size;
    }
}