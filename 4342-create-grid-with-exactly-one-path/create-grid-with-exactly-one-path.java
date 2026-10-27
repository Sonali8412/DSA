import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#'); // Fill the entire row with obstacles first
            
            if (i == 0) {
                // The first row is completely free (move all the way right)
                Arrays.fill(row, '.');
            } else {
                // For all other rows, only the last column is free (move down the right edge)
                row[n - 1] = '.';
            }
            
            grid[i] = new String(row);
        }
        
        return grid;
    }
}