import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();
        // Add building 1 with height 0
        list.add(new int[]{1, 0});
        
        // Add all given restrictions
        for (int[] r : restrictions) {
            list.add(r);
        }
        
        // Ensure building n is included if not already present
        boolean hasN = false;
        for (int[] r : restrictions) {
            if (r[0] == n) {
                hasN = true;
                break;
            }
        }
        if (!hasN) {
            list.add(new int[]{n, n - 1});
        }
        
        // Sort restrictions by building ID
        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        
        int m = list.size();
        
        // Left-to-right pass
        for (int i = 1; i < m; i++) {
            int idDiff = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i - 1)[1] + idDiff);
        }
        
        // Right-to-left pass
        for (int i = m - 2; i >= 0; i--) {
            int idDiff = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i + 1)[1] + idDiff);
        }
        
        // Find the maximum building height between adjacent restrictions
        int maxHeight = 0;
        for (int i = 1; i < m; i++) {
            int id1 = list.get(i - 1)[0];
            int h1 = list.get(i - 1)[1];
            int id2 = list.get(i)[0];
            int h2 = list.get(i)[1];
            
            int idDiff = id2 - id1;
            int peak = (h1 + h2 + idDiff) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        
        return maxHeight;
    }
}