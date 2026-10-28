import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    class State {
        int x, y, mask, energy, steps;
        State(int x, int y, int mask, int energy, int steps) {
            this.x = x;
            this.y = y;
            this.mask = mask;
            this.energy = energy;
            this.steps = steps;
        }
    }

    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        int litterCount = 0;
        char[][] grid = new char[m][n];
        
        // Find 'S' and assign an index to each 'L'
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = classroom[i].charAt(j);
                litterId[i][j] = -1;
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        int fullMask = (1 << litterCount) - 1;
        
        // bestEnergy[x][y][mask] keeps track of the maximum energy we've had at this cell with this mask
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                java.util.Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
        Queue<State> queue = new ArrayDeque<>();
        
        int initialMask = 0;
        if (litterId[startX][startY] != -1) {
            initialMask |= (1 << litterId[startX][startY]);
        }
        
        queue.offer(new State(startX, startY, initialMask, maxEnergy, 0));
        bestEnergy[startX][startY][initialMask] = maxEnergy;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            
            if (curr.mask == fullMask) {
                return curr.steps;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 'X') continue;
                
                int nEnergy = curr.energy - 1;
                if (nEnergy < 0) continue; // Cannot move if energy is exhausted
                
                int nMask = curr.mask;
                if (litterId[nx][ny] != -1) {
                    nMask |= (1 << litterId[nx][ny]);
                }
                
                // If it's a reset area, restore energy to maxEnergy
                if (grid[nx][ny] == 'R') {
                    nEnergy = maxEnergy;
                }
                
                // Prune if we have visited this state with equal or greater energy before
                if (nEnergy <= bestEnergy[nx][ny][nMask]) {
                    continue;
                }
                
                bestEnergy[nx][ny][nMask] = nEnergy;
                queue.offer(new State(nx, ny, nMask, nEnergy, curr.steps + 1));
            }
        }
        
        return -1;
    }
}