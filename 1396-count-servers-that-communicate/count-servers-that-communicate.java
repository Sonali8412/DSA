class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCounts = new int[m];
        int[] colCounts = new int[n];
        
        // Count the number of servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCounts[i]++;
                    colCounts[j]++;
                }
            }
        }
        
        int communicativeServers = 0;
        
        // Count servers that share their row or column with at least one other server
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (rowCounts[i] > 1 || colCounts[j] > 1) {
                        communicativeServers++;
                    }
                }
            }
        }
        
        return communicativeServers;
    }
}