import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        // maxHealth[r][c] stores the maximum health we can have when reaching cell (r, c)
        int[][] maxHealth = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHealth[i][j] = -1;
            }
        }

        // 0-1 BFS using a Deque. Elements: {row, col, current_health}
        Deque<int[]> deque = new ArrayDeque<>();

        int startCost = grid.get(0).get(0);
        int initialHealth = health - startCost;
        if (initialHealth <= 0) {
            return false;
        }

        maxHealth[0][0] = initialHealth;
        deque.offerFirst(new int[] { 0, 0, initialHealth });

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int h = curr[2];

            // If we reached the bottom-right corner with health >= 1
            if (r == m - 1 && c == n - 1) {
                return true;
            }

            // If we found a path to this cell with strictly more health previously, skip
            if (h < maxHealth[r][c]) {
                continue;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    int nextHealth = h - weight;

                    if (nextHealth > 0 && nextHealth > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nextHealth;
                        if (weight == 0) {
                            // Weight 0: push to the front (deque)
                            deque.offerFirst(new int[] { nr, nc, nextHealth });
                        } else {
                            // Weight 1: push to the back (deque)
                            deque.offerLast(new int[] { nr, nc, nextHealth });
                        }
                    }
                }
            }
        }

        return false;
    }
}