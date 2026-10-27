import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        int[][] dp = new int[n][n];
        int[][] count = new int[n][n];
        
        // Initialize DP tables with -1 to represent unreachable cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        
        // Base case: 'S' at the bottom-right corner
        dp[n - 1][n - 1] = 0;
        count[n - 1][n - 1] = 1;
        
        // Directions for moving backwards: Up, Left, Up-Left
        int[] dr = {-1, 0, -1};
        int[] dc = {0, -1, -1};
        
        // Traverse backwards from bottom-right to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip if current cell is unreachable or an obstacle (except 'S' and 'E')
                if (dp[r][c] == -1) continue;
                char currChar = board.get(r).charAt(c);
                if (currChar == 'X') continue;
                
                // Try all three previous moves (going up, left, or diagonally up-left)
                for (int i = 0; i < 3; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (nr >= 0 && nc >= 0) {
                        char nextChar = board.get(nr).charAt(nc);
                        if (nextChar == 'X') continue;
                        
                        // Value to add from the next cell (E and S have 0 value)
                        int val = (nextChar == 'E' || nextChar == 'S') ? 0 : (nextChar - '0');
                        int nextScore = dp[r][c] + val;
                        
                        if (nextScore > dp[nr][nc]) {
                            dp[nr][nc] = nextScore;
                            count[nr][nc] = count[r][c];
                        } else if (nextScore == dp[nr][nc]) {
                            count[nr][nc] = (count[nr][nc] + count[r][c]) % MOD;
                        }
                    }
                }
            }
        }
        
        // If 'E' (0,0) is still unreachable (-1), return [0, 0]
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{dp[0][0], count[0][0]};
    }
}