
import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;

        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(sortedQueries,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Sort logs by timestamp
        Arrays.sort(logs,
            (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        int[] serverCount = new int[n + 1];

        int activeServers = 0;

        int left = 0;
        int right = 0;

        int logLen = logs.length;

        for (int i = 0; i < m; i++) {

            int qTime = sortedQueries[i][0];
            int qIndex = sortedQueries[i][1];

            int startTime = qTime - x;

            // Step 1: Add all logs up to qTime
            while (right < logLen &&
                   logs[right][1] <= qTime) {

                int serverId = logs[right][0];

                if (serverCount[serverId] == 0) {
                    activeServers++;
                }

                serverCount[serverId]++;
                right++;
            }

            // Step 2: Remove logs outside the window
            while (left < right &&
                   logs[left][1] < startTime) {

                int serverId = logs[left][0];

                serverCount[serverId]--;

                if (serverCount[serverId] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Step 3: Count inactive servers
            ans[qIndex] = n - activeServers;
        }

        return ans;
    }
}
